package service;

import base.BaseTest;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import www.lesson.details.service.ClassDetailsService;
import www.lesson.details.service.TeacherDetailsService;
import www.lesson.pojo.Class;
import www.lesson.pojo.Page;
import www.lesson.pojo.Teacher;

import javax.annotation.Resource;

public class ServiceTest extends BaseTest{

    @Resource(name = "classDetailsServiceImpl")
    ClassDetailsService classDetailsService;

    @Resource(name = "teacherDetailsServiceImpl")
    TeacherDetailsService teacherDetailsService;


    @Test
    public void testServices() {

        Page<Teacher> page = new Page<Teacher>(1, 3);

        teacherDetailsService.listTeachers(page);

        System.out.println("测试service"+page.getResult().get(0).getId());

    }


}
