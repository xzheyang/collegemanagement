package www.lesson.details.service.impl;

import org.springframework.stereotype.Service;
import www.lesson.details.service.LessonDetailsService;
import www.lesson.lesson.dao.LessonDao;
import www.lesson.pojo.Lesson;
import www.lesson.pojo.Page;

import javax.annotation.Resource;


@Service
public class LessonDetailsServiceImpl implements LessonDetailsService {

    @Resource
    LessonDao lessonDao;

    public void listLessonsByClass(Page<Lesson> page, String classId) {

        page.setTotal(lessonDao.totalLessonByClass(classId));
        page.setResult(lessonDao.listLessonsByClass(classId,page.getStart(),page.getEnd()));

    }


}
