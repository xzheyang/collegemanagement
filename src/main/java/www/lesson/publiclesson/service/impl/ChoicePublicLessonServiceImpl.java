package www.lesson.publiclesson.service.impl;

import org.springframework.stereotype.Service;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.ChoicePublicLessonDao;
import www.lesson.publiclesson.dao.PublicLessonDao;
import www.lesson.publiclesson.service.ChoicePublicLessonService;

import javax.annotation.Resource;

@Service
public class ChoicePublicLessonServiceImpl implements ChoicePublicLessonService {

    @Resource(name = "redisChoicePublicLessonDao")
    ChoicePublicLessonDao choiceDao;

    @Resource(name = "redisPublicLessonDao")
    PublicLessonDao lessonDao;

    public void insertChoice(String studentId, String lessonId) {

        choiceDao.addChoice(studentId,lessonId);

    }


    public void deleteChoice(String studentId, String lessonId) {

        choiceDao.deleteChoice(studentId,lessonId);

    }

    public PublicLesson getChoice(String studentId) {

        String lessonId = choiceDao.getChoice(studentId);

        if(lessonId!=null){
            PublicLesson publicLesson = lessonDao.getPublicLesson(lessonId);
            //查询在线人数 没有VO将就用teacherId
            publicLesson.setTeacherId(String.valueOf(choiceDao.getChoiceCount(lessonId)));
            return publicLesson;
        }else{
            return null;
        }

    }


}
