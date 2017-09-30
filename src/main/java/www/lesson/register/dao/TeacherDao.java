package www.lesson.register.dao;

import org.springframework.stereotype.Repository;
import www.lesson.pojo.Teacher;

@Repository
public interface TeacherDao {

    int deleteByPrimaryKey(String id);

    boolean insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

}
