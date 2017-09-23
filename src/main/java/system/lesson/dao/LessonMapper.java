package \main\java\system\lesson\dao;

import \main\java\system\lesson\pojo.Lesson;

public interface LessonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
}