package www.lesson.register.dao;

import org.springframework.stereotype.Repository;
import www.lesson.pojo.Class;

@Repository
public interface ClassDao {

    int deleteByPrimaryKey(String id);

    boolean insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    //查询最后一条id值(注意mysql用的是首字母排序)
    String selectMaxId();
}
