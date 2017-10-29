package www.lesson.publiclesson.dao.impl;


import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.ChoicePublicLessonDao;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class RedisChoicePublicLessonDao implements ChoicePublicLessonDao {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    //已选课的学生的redis库hashMap的key
    final static String publicLessonStudent ="publicLessonStudent";
    //课程前缀
    final static String preRedisPublicLesson="publicLesson";

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

    public void addChoice(String studentId ,String lessonId) {

        //自动生成classId
        String redisPublicLesson = preRedisPublicLesson + lessonId;


        ShardedJedis shardedJedis = getShardedJedis();


        if (shardedJedis == null) {
            throw new RuntimeException("服务器出错");
        }

        try {

            if (shardedJedis.hexists(publicLessonStudent, studentId)) {
                throw new RuntimeException("已选公选课");
            }


            long scoreKey = Long.parseLong(lessonId);
            long exist = shardedJedis.zcount(RedisPublicLessonDao.publicLessonDetails.getBytes(),scoreKey,scoreKey);
            if (exist==0) {
                throw new RuntimeException("无此公选课");
            }

            //获得公选课详细信息
            byte[] bytes;
            PublicLesson publicLesson = null;
            Set set = shardedJedis.zrangeByScore(RedisPublicLessonDao.publicLessonDetails.getBytes(),scoreKey,scoreKey);
            Iterator it = set.iterator();
            while(it.hasNext()){
                bytes = (byte[]) it.next();
                publicLesson = schema.newMessage();
                //反序列化
                ProtostuffIOUtil.mergeFrom(bytes, publicLesson, schema);
            }


            //简单考虑多线程
            synchronized (this) {                       //限制人数
                if (shardedJedis.scard(redisPublicLesson) >= publicLesson.getAmount()) {
                    throw new RuntimeException("公选课已满");
                }

                //添加到指定课程
                shardedJedis.sadd(redisPublicLesson, studentId);

            }

            //添加到已选学生
            shardedJedis.hsetnx(publicLessonStudent,studentId,lessonId);

        }finally {
            shardedJedis.disconnect();
        }

    }


    public void deleteChoice(String studentId ,String lessonId) {

        ShardedJedis shardedJedis = getShardedJedis();

        if (shardedJedis == null) {
            throw new RuntimeException("服务器出错");
        }

        try {

            String redisPublicLesson = preRedisPublicLesson + lessonId;

            //先删除公选课SET的数据
            shardedJedis.srem(redisPublicLesson, studentId);
            //再删除已选学生的数据
            shardedJedis.hdel(publicLessonStudent, studentId);

        }finally {
            shardedJedis.disconnect();
        }

    }


    public Map<String ,List<String>> listChoice(){

        ShardedJedis shardedJedis = getShardedJedis();

        if (shardedJedis == null) {
            throw new RuntimeException("服务器出错");
        }

        //设置结果值
        Map<String ,List<String>> result = new HashMap<String, List<String>>();
        List<String> lessonStudents;
        Set<String> students;

        try {

            students = shardedJedis.hkeys(publicLessonStudent);

        }finally {
            shardedJedis.disconnect();
        }

        Iterator<String> it = students.iterator();
        while (it.hasNext()) {
            String student = it.next();
            String lesson = shardedJedis.hget(publicLessonStudent, student);

            if (result.containsKey(lesson)) {

                lessonStudents = result.get(lesson);

            } else {
                lessonStudents = new ArrayList<String>();
                result.put(lesson, lessonStudents);
            }

            lessonStudents.add(student);
        }


        return result;
    }

    public Set<String> listChoiceByLessonId(String lessonId) {

        ShardedJedis shardedJedis = getShardedJedis();
        Set<String> result;

        if (shardedJedis == null) {
            throw new RuntimeException("服务器出错");
        }



        try {

          result= shardedJedis.smembers(preRedisPublicLesson+lessonId);

        }finally {
            shardedJedis.disconnect();
        }



        return result;
    }

    public String getChoice(String studentId) {
        ShardedJedis shardedJedis = getShardedJedis();
        String lessonId;

        if (shardedJedis == null) {
            throw new RuntimeException("服务器出错");
        }

        try {

             lessonId = shardedJedis.hget(publicLessonStudent, studentId);


        }finally {
            shardedJedis.disconnect();
        }

        return lessonId;
    }

    public long getChoiceCount(String lessonId){
        ShardedJedis shardedJedis = getShardedJedis();
        String redisPublicLesson = preRedisPublicLesson+lessonId;
        long count;

        if (shardedJedis == null) {
            throw new RuntimeException("服务器出错");
        }

        try{

           count = shardedJedis.scard(redisPublicLesson);

        }finally {
            shardedJedis.disconnect();
        }

        return count;
    }

}
