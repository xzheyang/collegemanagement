package dao;

import base.BaseTest;
import org.junit.Test;
import www.lesson.pojo.Class;
import www.lesson.pojo.Student;
import www.lesson.pojo.User;
import www.lesson.register.dao.ClassDao;
import www.lesson.system.dao.UserDao;

import javax.annotation.Resource;

public class DetailsDaoTest extends BaseTest {

    @Resource
    UserDao userDao;

    @Resource
    ClassDao classDao;

    @Test
    public void testDetails() {
        User user = new User();
        user.setId("6");
        user.setPassword("22");
        user.setRoleId("1");
        user.setBlock(true);
        userDao.insert(user);
        System.out.println("测试:"+classDao.selectMaxId());
    }


}
