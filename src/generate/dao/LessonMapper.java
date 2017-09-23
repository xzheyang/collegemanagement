package \generate\dao;

import \generate\pojo.Lesson;

public interface LessonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
}