package www.lesson.lesson.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import www.lesson.pojo.Score;
import www.lesson.pojo.ScoreKey;


import java.util.List;

@Repository
public interface ScoreDao {

    int deleteByPrimaryKey(ScoreKey key);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(ScoreKey key);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    //查询学生成绩总数
    int countByStudentId(String studentId);
    //查询课程成绩总数
    int countByLessonId(int lessonId);
    //分页显示学生成绩
    List<Score> selectByStudentId(@Param("studentId") String studentId,
                                  @Param("start") int start, @Param("end") int end);
    //分页课程学生成绩
    List<Score> selectByLessonId(@Param("lessonId") int lessonId,
                                 @Param("start") int start, @Param("end") int end);

}
