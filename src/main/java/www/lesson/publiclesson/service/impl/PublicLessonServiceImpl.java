package www.lesson.publiclesson.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.pojo.PublicLesson;
import www.lesson.pojo.Score;
import www.lesson.publiclesson.dao.PublicLessonDao;
import www.lesson.publiclesson.service.PublicLessonService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class PublicLessonServiceImpl implements PublicLessonService {

    @Resource(name = "redisPublicLessonDao")
    PublicLessonDao dao;

    public void insert(PublicLesson publicLesson) {
        dao.insertPublicLessonNoId(publicLesson);
    }

    public void update(PublicLesson publicLesson) {
        dao.updatePublicLesson(publicLesson);
    }

    public void updateWithId(PublicLesson publicLesson,String oldId){
        dao.deletePublicLesson(oldId);

        if(dao.existPublicLesson(String.valueOf(publicLesson.getId()))){
            throw new RuntimeException("修改Id已存在");
        }
        dao.insertPublicLesson(publicLesson);
    }

    public void insertWithId(PublicLesson publicLesson) {
        if(dao.existPublicLesson(String.valueOf(publicLesson.getId()))){
            throw new RuntimeException("修改Id已存在");
        }
        dao.insertPublicLesson(publicLesson);
    }

    public void delete(String Id) {
        dao.deletePublicLesson(Id);
    }

    public void insert(File file) {

        InputStream in = null;

        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<List<Object>> l1 = ExcelUtils.upExcel(in,file.getName());

        for(int i=0;i<l1.size();i++){
            List<Object> l2 = l1.get(i);

            PublicLesson lesson = new PublicLesson();
            lesson.setName(String.valueOf(l2.get(0)));
            lesson.setTeacherName(String.valueOf(l2.get(1)));
            lesson.setTeacherId(String.valueOf(l2.get(2)));
            lesson.setTime(String.valueOf(l2.get(3)));
            lesson.setCredit(Integer.parseInt(String.valueOf(l2.get(4))));


            try {
                dao.insertPublicLesson(lesson);
            }catch (Exception e){
                throw new RuntimeException("第"+String.valueOf(i+2)+"行数据错误");
            }
        }
    }


}
