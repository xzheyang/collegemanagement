package www.lesson.register.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import www.lesson.pojo.Teacher;

import java.util.List;

@Repository
public interface TeacherDao {

    int deleteByPrimaryKey(String id);

    boolean insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    //查询有多少教师数量
    int totalTeachers();

    //查询有多少教师 ,分页
    List<Teacher> listTeachers(@Param("start")int start , @Param("end") int end);

}
