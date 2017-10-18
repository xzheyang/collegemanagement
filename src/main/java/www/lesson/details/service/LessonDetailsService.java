package www.lesson.details.service;

import www.lesson.pojo.Lesson;
import www.lesson.pojo.Page;

import java.util.List;

public interface LessonDetailsService {

    //按班级查询课程
    void listLessonsByClass(Page<Lesson> page, String classId);

    //查询有多少课程,这里又需要VO???

    //查询此老师id有多少课程
    List<Lesson> listLessonsByTeacher(String teacherId);

}
