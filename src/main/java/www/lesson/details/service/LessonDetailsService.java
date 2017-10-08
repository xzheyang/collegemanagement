package www.lesson.details.service;

import www.lesson.pojo.Lesson;
import www.lesson.pojo.Page;

public interface LessonDetailsService {

    //按班级查询课程
    void listLessonsByClass(Page<Lesson> page, String classId);

}
