package www.lesson.details.service;

import org.springframework.stereotype.Service;
import www.lesson.pojo.Page;
import www.lesson.pojo.PublicLesson;


public interface PublicLessonDetailsService {

    //显示公选课
    void listPublicLesson(Page<PublicLesson> page);
    //显示学生公选课(和在线人数)
    void listPublicLessonForS(Page<PublicLesson> page);



}
