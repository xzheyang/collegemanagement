package www.lesson.details.service.impl;

import org.springframework.stereotype.Service;
import www.lesson.details.service.PublicLessonDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.ChoicePublicLessonDao;
import www.lesson.publiclesson.dao.PublicLessonDao;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PublicLessonDetailsServiceImpl implements PublicLessonDetailsService {

    @Resource(name = "redisPublicLessonDao")
    PublicLessonDao dao;
    @Resource(name = "redisChoicePublicLessonDao")
    ChoicePublicLessonDao choiceDao;


    public void listPublicLesson(Page<PublicLesson> page) {
        page.setTotal(dao.countPublicLesson());
        page.setResult(dao.listPublicLesson(page.getStart(),page.getEnd()));
    }

    public void listPublicLessonForS(Page<PublicLesson> page) {
        List<PublicLesson> lessons = dao.listPublicLesson(page.getStart(),page.getEnd());
        // 没设计VO将就用teacherId替换在线人数

        for(int i=0;i<lessons.size();i++){
            PublicLesson lesson = lessons.get(i);
            lesson.setTeacherId(String.valueOf(choiceDao.getChoiceCount(lesson.getId())));
        }

        page.setTotal(dao.countPublicLesson());
        page.setResult(lessons);
    }

}
