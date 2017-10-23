package www.lesson.publiclesson.service;

import www.lesson.pojo.PublicLesson;

import java.util.List;
import java.util.Map;

public interface ChoicePublicLessonService {

    void insertChoice(String studentId ,String lessonId);
    void deleteChoice(String studentId ,String lessonId);

    //Map<String ,List<String>> listChoice();
    PublicLesson getChoice(String studentId);

}
