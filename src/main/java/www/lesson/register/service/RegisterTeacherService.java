package www.lesson.register.service;

import org.apache.poi.ss.formula.functions.T;
import www.lesson.pojo.Teacher;

import java.io.File;

public interface RegisterTeacherService {

    //使用Excel注册班级信息
    void registerTeacherByExcel(File file);

    //单独注册班级信息
    boolean registerTeacher(Teacher teacher);

    //意外修改教师师信息
    boolean updateTeacher(Teacher teacher);

    //意外删除教师信息  同步删除user
    boolean deleteTeacher(String id);


}
