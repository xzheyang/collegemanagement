package www.lesson.details.service;


import www.lesson.pojo.Page;
import www.lesson.pojo.Teacher;

public interface TeacherDetailsService {

        //分页显示所有教师
    void listTeachers (Page<Teacher> page);

        //搜索教师通过id
    Teacher searchTeacher(String id);

}
