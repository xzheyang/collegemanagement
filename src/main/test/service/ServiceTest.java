package service;

import base.BaseTest;
import org.junit.Test;
import www.lesson.backup.service.BackupService;
import www.lesson.details.service.ClassDetailsService;
import www.lesson.details.service.TeacherDetailsService;
import www.lesson.publiclesson.service.ChoicePublicLessonService;
import www.lesson.publiclesson.service.PublicLessonService;

import javax.annotation.Resource;
import java.util.List;

public class ServiceTest extends BaseTest{

    @Resource(name = "classDetailsServiceImpl")
    ClassDetailsService classDetailsService;

    @Resource(name = "teacherDetailsServiceImpl")
    TeacherDetailsService teacherDetailsService;

    @Resource(name = "choicePublicLessonServiceImpl")
    ChoicePublicLessonService choiceService;

    @Resource(name = "publicLessonServiceImpl")
    PublicLessonService publicLessonService;

    @Resource(name = "backupServiceImpl")
    BackupService backupService;

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



        //System.out.println("测试service"+1);
        //choiceService.deleteChoice("hy","8");


        //backupService.copyAllDataNow();
        //backupService.deleteData();


        List<String> result = backupService.listAllData();

        String time = result.get(0);
        System.out.println(time);
        backupService.restoreData(time);

    }


}
