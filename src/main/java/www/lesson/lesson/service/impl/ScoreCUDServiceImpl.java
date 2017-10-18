package www.lesson.lesson.service.impl;

import org.springframework.stereotype.Service;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.lesson.dao.ScoreDao;
import www.lesson.lesson.service.ScoreCUDService;
import www.lesson.pojo.Score;
import www.lesson.pojo.ScoreKey;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
public class ScoreCUDServiceImpl implements ScoreCUDService {

    @Resource
    ScoreDao scoreDao;


    public void insertByExcel(File file) {
        try {

            InputStream in = new FileInputStream(file);

            List<List<Object>> l1 = ExcelUtils.upExcel(in,file.getName());



            for(int i=0;i<l1.size();i++){
                List<Object> l2 = l1.get(i);

                Score score = new Score();
                score.setLessonId(Integer.valueOf(String.valueOf(l2.get(0))));
                score.setStudentId(String.valueOf(l2.get(1)));
                score.setScore(Integer.valueOf(String.valueOf(l2.get(2))));

                try {
                    scoreDao.insert(score);

                }catch (Exception e){
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行数据错误");
                }
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException("未找到文件");
        }


    }


    public void insert(Score score) {
       scoreDao.insert(score);
    }

    public void update(Score score) {
        scoreDao.updateByPrimaryKey(score);
    }

    public void delete(ScoreKey scoreKey) {
        scoreDao.deleteByPrimaryKey(scoreKey);
    }


}
