package dao;

import base.BaseTest;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import www.lesson.lesson.dao.LessonDao;
import www.lesson.pojo.*;
import www.lesson.pojo.Class;
import www.lesson.register.dao.ClassDao;
import www.lesson.register.dao.StudentDao;
import www.lesson.register.dao.TeacherDao;
import www.lesson.system.dao.UserDao;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetailsDaoTest extends BaseTest {

    @Resource
    UserDao userDao;

    @Resource
   StudentDao studentDao;


    @Resource
    TeacherDao teacherDao;

    @Resource
    ClassDao classDao;

    @Resource
    LessonDao lessonDao;

    @Test
    public void testDetails() {

        Lesson l = new Lesson();

        l.setName("2");
        l.setSession("3");
        l.setTeacherId("7");



        System.out.println("测试dao1:"+lessonDao.totalLessonByClass("2014003"));
        //System.out.println("pojo_id:"+l.getId()+"  pojo_s:"+l.getSession());
        System.out.println("测试dao2:"+lessonDao.listLessonsByClass("2014003",0,3).get(2).getSession());

    }


}
