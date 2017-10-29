package www.lesson.publiclesson.service;

import www.lesson.pojo.PublicLesson;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ChoicePublicLessonService {

    void insertChoice(String studentId ,String lessonId);
    void deleteChoice(String studentId ,String lessonId);

    //获得全部课程的学生信息(学生id,班级学生名)的Excel
    void listChoice(File file);
    //获得指定课程Id的学生信息的Excel
    void listChoiceByLessonId(String lessonId,File file);
    //获得指定studentId的选择课程的VO
    PublicLesson getChoice(String studentId);

}
