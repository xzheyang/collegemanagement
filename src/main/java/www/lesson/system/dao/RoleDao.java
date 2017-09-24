package www.lesson.system.dao;

import org.springframework.stereotype.Repository;
import www.lesson.pojo.Role;

@Repository
public interface RoleDao {

    int deleteById(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectById(String id);

    int updateByIdSelective(Role record);

    int updateById(Role record);

}
