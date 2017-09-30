package www.lesson.register.dao;

import org.springframework.stereotype.Repository;
import www.lesson.pojo.Student;

@Repository
public interface StudentDao {

    int deleteByPrimaryKey(String id);

    boolean insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}
