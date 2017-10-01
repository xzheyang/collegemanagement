package www.lesson.details.service;

import www.lesson.pojo.Page;
import www.lesson.pojo.Student;

public interface StudentDetails {

    //分页显示此班级有多少学生
    Page<Student> listStudentsByClass(Page<Student> page,String classId);

}
