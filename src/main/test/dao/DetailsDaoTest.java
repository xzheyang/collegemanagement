package dao;

import base.BaseTest;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import www.lesson.lesson.dao.LessonDao;
import www.lesson.lesson.dao.ScoreDao;
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

    @Resource
    ScoreDao scoreDao;

    @Test
    public void testDetails() {


        System.out.println("测试"+scoreDao.selectByLessonId(1,0,8).get(0).getScore());

    }


}
