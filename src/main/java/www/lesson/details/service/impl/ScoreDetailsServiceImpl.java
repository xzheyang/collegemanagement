package www.lesson.details.service.impl;

import org.springframework.stereotype.Service;
import www.lesson.details.service.ScoreDetailsService;
import www.lesson.lesson.dao.ScoreDao;
import www.lesson.pojo.Page;
import www.lesson.pojo.Score;

import javax.annotation.Resource;

@Service
public class ScoreDetailsServiceImpl  implements ScoreDetailsService{

    @Resource
    ScoreDao scoreDao;

    public void listScoreByStudentId(Page<Score> page, String studentId) {

        page.setTotal(scoreDao.countByStudentId(studentId));
        page.setResult(scoreDao.selectByStudentId(studentId,page.getStart(),page.getEnd()));

    }

    public void listScoreByLessonId(Page<Score> page, String LessonId) {

        page.setTotal(scoreDao.countByLessonId(Integer.parseInt(LessonId)));
        page.setResult(scoreDao.selectByLessonId(Integer.parseInt(LessonId),page.getStart(),page.getEnd()));

    }

}
