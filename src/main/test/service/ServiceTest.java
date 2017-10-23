package service;

import base.BaseTest;

import org.junit.Test;
import www.lesson.details.service.ClassDetailsService;
import www.lesson.details.service.TeacherDetailsService;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.service.ChoicePublicLessonService;
import www.lesson.publiclesson.service.PublicLessonService;

import javax.annotation.Resource;

public class ServiceTest extends BaseTest{

    @Resource(name = "classDetailsServiceImpl")
    ClassDetailsService classDetailsService;

    @Resource(name = "teacherDetailsServiceImpl")
    TeacherDetailsService teacherDetailsService;

    @Resource(name = "choicePublicLessonServiceImpl")
    ChoicePublicLessonService choiceService;

    @Resource(name = "publicLessonServiceImpl")
    PublicLessonService publicLessonService;

    @Test
    public void testServices() {
        /*
        PublicLesson lesson = new PublicLesson();
        lesson.setName("中东历史");
        lesson.setTeacherId("7");
        lesson.setTime("星期四");
        lesson.setAmount(50L);
        lesson.setCredit(1);
        lesson.setTeacherName("ccc");


        publicLessonService.insert(lesson);*/


        for(int i=140;i<200;i++){
            choiceService.insertChoice(String.valueOf(i),"8");
        }

        //System.out.println("测试service"+1);
        //choiceService.deleteChoice("hy","8");
    }


}
