package www.lesson.details.service;

import www.lesson.pojo.Class;
import www.lesson.pojo.Page;
import www.lesson.pojo.Student;
import www.lesson.vo.StudentSimpleVO;

import java.util.List;

public interface StudentDetailsService {

    //分页显示此班级有多少学生
    void listStudentsByClass(Page<Student> page,String classId);

    //搜索学生通过id
    Student searchStudent(String id);

    //获得学生班级简单信息
    StudentSimpleVO searchStudentSimpleVO(String id);

}
