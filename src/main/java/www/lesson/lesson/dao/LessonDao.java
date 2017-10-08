package www.lesson.lesson.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import www.lesson.pojo.Lesson;

import java.util.List;

@Repository
public interface LessonDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
    //添加课程与班级的关系
    boolean insertRelativity(@Param("lessonId") String lessonId,@Param("classId") String classId);
    //删除课程与班级的关系
    boolean deleteRelativity(@Param("lessonId") String lessonId,@Param("classId") String classId);
    //删除所有关系指定课程与相关班级的关系
    boolean deleteAllRelativity(String lessonId);
    //查询指定课程与班级的关系
    List<String> listRelativity(String lessonId);
    //查询指定班级的课程
    List<Lesson> listLessonsByClass(@Param("classId") String classId,
                                    @Param("start")int start ,@Param("end") int end);
    //查询此班级课程的总数
    int totalLessonByClass(String classId);

}
