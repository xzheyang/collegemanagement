package dao;

import base.BaseTest;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
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

    @Test
    public void testDetails() {

        List<Teacher> lt = teacherDao.listTeachers(0,3);

        System.out.println("测试dao:"+lt.get(0).getId());

        System.out.println("测试dao2:"+teacherDao.totalTeachers());

    }


}
