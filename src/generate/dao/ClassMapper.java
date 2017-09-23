package \generate\dao;

import \generate\pojo.Class;

public interface ClassMapper {
    int deleteByPrimaryKey(String id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}