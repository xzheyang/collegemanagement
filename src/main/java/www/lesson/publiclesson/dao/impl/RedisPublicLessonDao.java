package www.lesson.publiclesson.dao.impl;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.PublicLessonDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class RedisPublicLessonDao implements PublicLessonDao {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    //公选课的redis库的key
    final static String publicLessonDetails ="publicLessonDetails";

    private RuntimeSchema<PublicLesson> schema = RuntimeSchema.createFrom(PublicLesson.class);

    private ShardedJedis getShardedJedis(){

        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            shardedJedis.disconnect();
        }
        return null;

    }

    public void insertPublicLesson(PublicLesson publicLesson) {

        ShardedJedis shardedJedis = getShardedJedis();

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{
            //序列化
            byte[] publicLessonBytes = ProtostuffIOUtil.toByteArray(publicLesson, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));



            shardedJedis.zadd(publicLessonDetails.getBytes(), Double.parseDouble(publicLesson.getId()), publicLessonBytes);


        }finally {
            shardedJedis.disconnect();
        }

    }

    public void insertPublicLessonNoId(PublicLesson publicLesson) {

        ShardedJedis shardedJedis = getShardedJedis();

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{
            byte[] bytes = null;
            //获得指定的最大score的值自动生成Id
            Set set = shardedJedis.zrevrange(publicLessonDetails.getBytes(),0,0);
            Iterator it = set.iterator();
            while(it.hasNext()){
                bytes = (byte[]) it.next();
            }

            //自动生成公选课Id,放在这而不是service的考虑是速度,而多线程又不得不考虑安全
           synchronized (this){

            try{
                publicLesson.setId(String.valueOf(shardedJedis.zscore(publicLessonDetails.getBytes(),bytes).intValue()+1));
            }catch (NullPointerException e){
                publicLesson.setId("1");
            }

            //序列化
            byte[] publicLessonBytes = ProtostuffIOUtil.toByteArray(publicLesson, schema,
                   LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));


            shardedJedis.zadd(publicLessonDetails.getBytes(),Double.parseDouble(publicLesson.getId()) , publicLessonBytes);

            }

        }finally {
           shardedJedis.disconnect();
        }


    }

    public void deletePublicLesson(String Id) {

        ShardedJedis shardedJedis = getShardedJedis();

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{

            shardedJedis.zremrangeByScore(publicLessonDetails.getBytes(),Id.getBytes(),Id.getBytes());

        }finally {
            shardedJedis.disconnect();
        }
    }



    public void updatePublicLesson(PublicLesson publicLesson) {

        ShardedJedis shardedJedis = getShardedJedis();
        double id = Double.parseDouble(publicLesson.getId());

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{

            long exist = shardedJedis.zcount(publicLessonDetails.getBytes(),id,id);
            if(exist==0){
                throw new RuntimeException("数据已删除");
            }


            //序列化
            byte[] publicLessonBytes = ProtostuffIOUtil.toByteArray(publicLesson, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

            shardedJedis.zremrangeByScore(publicLessonDetails.getBytes(), id, id);
            shardedJedis.zadd(publicLessonDetails.getBytes(), id, publicLessonBytes);

        }finally {
            shardedJedis.disconnect();
        }

    }

    public PublicLesson getPublicLesson(String Id) {
        ShardedJedis shardedJedis = getShardedJedis();

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        Set set;
        PublicLesson publicLesson = null;

        try{

           set = shardedJedis.zrevrangeByScore(publicLessonDetails.getBytes(),Long.parseLong(Id),Long.parseLong(Id));

        }finally {
            shardedJedis.disconnect();
        }
        //反序列化
        Iterator it = set.iterator();
        while (it.hasNext()){

            byte[] bytes = (byte[]) it.next();

            if (bytes != null) {
                publicLesson = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, publicLesson, schema);
            }
        }

        return publicLesson;
    }

    public long countPublicLesson() {

        ShardedJedis shardedJedis = getShardedJedis();

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{

            return shardedJedis.zcard(publicLessonDetails.getBytes());

        }finally {
            shardedJedis.disconnect();
        }


    }

    public List<PublicLesson> listPublicLesson(int start,int end) {

        List<PublicLesson> lessons = new ArrayList<PublicLesson>();
        Set lessonBytes;
        ShardedJedis shardedJedis = getShardedJedis();


        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{
             //从redis获得课程
             lessonBytes = shardedJedis.zrevrange(publicLessonDetails.getBytes(),start,end);
             //从redis获得在线人数

        }finally {
            shardedJedis.disconnect();
        }

        //遍历并反序列化,放入List返回
        Iterator it = lessonBytes.iterator();
        while (it.hasNext()){

            byte[] bytes = (byte[]) it.next();

            if (bytes != null) {
                PublicLesson publicLesson = schema.newMessage();
                //反序列化
                ProtostuffIOUtil.mergeFrom(bytes, publicLesson, schema);
                lessons.add(publicLesson);
            }
        }

        return lessons;
    }

    public boolean existPublicLesson(String Id) {
        long exist;
        ShardedJedis shardedJedis = getShardedJedis();

        if(shardedJedis==null){
            throw new RuntimeException("服务器出错");
        }

        try{

            exist = shardedJedis.zcount(RedisPublicLessonDao.publicLessonDetails,Id,Id);

        }finally {
            shardedJedis.disconnect();
        }


        if(exist==0){
            return false;
        }else{
            return true;
        }


    }


}
