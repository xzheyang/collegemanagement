package dao;

import base.BaseTest;
import org.junit.Test;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.ChoicePublicLessonDao;
import www.lesson.publiclesson.dao.PublicLessonDao;

import javax.annotation.Resource;


public class DetailsDaoTest extends BaseTest {

    @Resource(name = "redisChoicePublicLessonDao")
    ChoicePublicLessonDao choiceDao;

    @Resource(name = "redisPublicLessonDao")
    PublicLessonDao publicLessonDao;
    @Test
    public void testDetails() {

        String textLua ="if redis.call( 'HEXISTS', hexPublicStudent , KEYS[2] ) ~=0 then\n"
                + "return false\n"
                +  "else\n"
                +"if = redis.call('SCARD', KEYS[3] ) >= 200\n"
                +"then \n"
                +"return false \n"
                +" else\n"
                +"redis.call('SADD', KEYS[3], KEYS[2] );\n"
                +"redis.call('HMSET', hexPublicStudent , KEYS[2], KEYS[3]);\n"
                +"return successed\n"
                +"end\n"
                +"end\n";



        final String studentId = "xx";
        final String classId= "110";


        PublicLesson publicLesson = new PublicLesson();

        //publicLesson.setId("2");
        publicLesson.setName("ss");
        publicLessonDao.insertPublicLessonNoId(publicLesson);
        //publicLessonDao.updatePublicLesson(publicLesson);

        //publicLessonDao.deletePublicLesson("6");



        //choiceDao.listChoice();



        /*
        //choiceDao.addChoice(studentId,classId);

        for(int i=0;i<500;i++) {

                    //choiceDao.addChoice(String.valueOf(i), classId);


        }
        final PublicLessonChoiceRedisDao dao = new PublicLessonChoiceRedisDao();

        new Thread(new Runnable() {

            public void run() {
                dao.addChoice(String.valueOf(2), classId);
            }
        }).start();
        */

    }




}
