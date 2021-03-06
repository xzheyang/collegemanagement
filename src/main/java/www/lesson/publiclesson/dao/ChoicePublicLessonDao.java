package www.lesson.publiclesson.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChoicePublicLessonDao {

    //添加学生选择
    void addChoice(String studentId ,String lessonId);
    //去除学生选择
    void deleteChoice(String studentId ,String lessonId);

    //显示课程全部学生选择
    Map<String ,List<String>> listChoice();
    //显示指定课程Id的学生信息
    Set<String> listChoiceByLessonId(String lessonId);

    //显示单个学生的选择
    String getChoice(String studentId);

    //显示在线人数
    long getChoiceCount(String lessonId);

}
