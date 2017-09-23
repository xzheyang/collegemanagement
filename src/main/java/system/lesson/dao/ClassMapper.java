package \main\java\system\lesson\dao;

import \main\java\system\lesson\pojo.Class;

public interface ClassMapper {
    int deleteByPrimaryKey(String id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}