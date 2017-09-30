package www.lesson.register.service;

import www.lesson.pojo.Student;

import java.io.File;

public interface RegisterStudentService {


    //通过excel注册学生信息
    void registerStudentByExcel(File file);

    //需要班级id的注册接口
    void regStuByExcelNeedId(File file);

    //单独注册学生
    boolean registerStudent(Student student);

}
