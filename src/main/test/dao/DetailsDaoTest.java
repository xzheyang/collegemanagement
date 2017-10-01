package dao;

import base.BaseTest;
import org.junit.Test;
import www.lesson.pojo.Class;
import www.lesson.pojo.Student;
import www.lesson.pojo.User;
import www.lesson.register.dao.ClassDao;
import www.lesson.register.dao.StudentDao;
import www.lesson.system.dao.UserDao;

import javax.annotation.Resource;
import java.util.Collections;

public class DetailsDaoTest extends BaseTest {

    @Resource
    UserDao userDao;

    @Resource
    StudentDao studentDao;

    @Resource
    ClassDao classDao;

    @Test
    public void testDetails() {


        System.out.println("测试:"+ studentDao.listPreStudentIds("2014002").size());
    }


}
