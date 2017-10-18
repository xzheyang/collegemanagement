package www.lesson.lesson.service;

import www.lesson.pojo.Score;
import www.lesson.pojo.ScoreKey;

import java.io.File;

public interface ScoreCUDService {

    void insertByExcel(File file);

    void insert(Score score);

    void update(Score score);

    void delete(ScoreKey scoreKey);

}
