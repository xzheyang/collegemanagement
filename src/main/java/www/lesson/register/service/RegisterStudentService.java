package www.lesson.register.service;

import www.lesson.pojo.Student;

import java.io.File;

public interface RegisterStudentService {


    //通过excel注册学生信息
    void registerStudentByExcel(File file);

    //需要学生id的注册接口
    void regStuByExcelNeedId(File file);

    //单独注册学生
    boolean registerStudent(Student student);

    //意外删除,要将User也删除
    boolean deleteStudent(String id);

    //意外修改学生
    boolean updateStudent(Student student);





}
