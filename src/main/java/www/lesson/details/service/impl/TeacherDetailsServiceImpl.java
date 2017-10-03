package www.lesson.details.service.impl;


import org.springframework.stereotype.Service;
import www.lesson.details.service.TeacherDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.Teacher;
import www.lesson.register.dao.TeacherDao;

import javax.annotation.Resource;

@Service
public class TeacherDetailsServiceImpl implements TeacherDetailsService {

    @Resource
    TeacherDao teacherDao;

    public void listTeachers(Page<Teacher> page) {

        //设置教师总数
        page.setTotal(teacherDao.totalTeachers());

        //设置教师信息
        page.setResult(teacherDao.listTeachers(page.getStart(),page.getEnd()));


    }

    public Teacher searchTeacher(String id) {
        return teacherDao.selectByPrimaryKey(id);
    }


}
