package www.lesson.system.dao;

import org.springframework.stereotype.Repository;
import www.lesson.pojo.User;

@Repository
public interface UserDao {

    int deleteById(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectById(String id);

    int updateByIdSelective(User record);

    int updateById(User record);

    User selectStudentById(String id);

    User selectTeacherById(String id);

}
