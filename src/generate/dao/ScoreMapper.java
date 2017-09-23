package \generate\dao;

import \generate\pojo.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}