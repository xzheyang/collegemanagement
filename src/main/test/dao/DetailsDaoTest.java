package dao;

import base.BaseTest;
import org.junit.Test;
import www.lesson.backup.service.BackupService;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.pojo.Lesson;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.ChoicePublicLessonDao;
import www.lesson.publiclesson.dao.PublicLessonDao;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class DetailsDaoTest extends BaseTest {

    @Resource(name = "redisChoicePublicLessonDao")
    ChoicePublicLessonDao choiceDao;

    @Resource(name = "redisPublicLessonDao")
    PublicLessonDao publicLessonDao;


    @Test
    public void testDetails() {



        /*
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
