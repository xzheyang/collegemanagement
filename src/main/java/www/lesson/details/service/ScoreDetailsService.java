package www.lesson.details.service;

import www.lesson.pojo.Page;
import www.lesson.pojo.Score;

public interface ScoreDetailsService {

    //分页显示学生所有成绩
    void listScoreByStudentId(Page<Score> page, String studentId);

    //分页显示课程所有注册
    void listScoreByLessonId(Page<Score> page, String LessonId);


}
