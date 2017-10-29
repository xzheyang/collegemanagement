package www.lesson.lesson.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.common.utils.ExcelFomart;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.lesson.dao.LessonDao;
import www.lesson.lesson.service.LessonCUDService;
import www.lesson.pojo.Lesson;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LessonCUDServiceImpl implements LessonCUDService {

    @Resource
    LessonDao lessonDao;

    //现在采取Controller处理异常
    //TODO 详细了解事务,优化事务

    //Excel需要 名字,学期(2000/9,2000/3),老师Id,班级Ids(用;隔开),
    public void insertLessons(File file) {

        try {


            InputStream in = new FileInputStream(file);

            //解析数据
            List<List<Object>> l1 = ExcelUtils.upExcel(in, file.getName());


            List<Lesson> lessons = new ArrayList<Lesson>();
            List<String[]> relatives = new ArrayList<String[]>();

            //遍历数据
            for(int i=0;i<l1.size();i++) {
                List<Object> l2 = l1.get(i);

                if(ExcelFomart.excelLessonFomart(String.valueOf(l2.get(0)),
                        String.valueOf(l2.get(1)))){


                Lesson lesson = new Lesson();

                lesson.setName(String.valueOf(l2.get(0)));
                lesson.setSession(String.valueOf(l2.get(1)));
                lesson.setTeacherId(String.valueOf(l2.get(2)));
                //ArrayList可以放入NULL
                String[] ids = String.valueOf(l2.get(3)).split(";");

                if(ids.length==0){
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现班级Ids为空错误");
                }

                relatives.add(ids);
                lessons.add(lesson);
                }else{
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现格式错误");
                }

            }


            for(int i=0;i<lessons.size();i++){

                try {
                    Lesson lesson = lessons.get(i);

                    //添加班级
                    lessonDao.insert(lesson);
                    //添加关系
                    String[] relative = relatives.get(i);
                    for (int j = 0; j < relative.length; j++) {

                        lessonDao.insertRelativity(lesson.getId(), relative[j]);
                    }

                }catch (Exception e){
                    throw new RuntimeException("第"+String.valueOf(i+2)+"行出现班级注册错误");
                }

            }



        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }



    }

    public void insertLesson(Lesson lesson, String[] classIds) {

        //添加   所以这里必须要在xml中设置主键自增
        lessonDao.insert(lesson);
        String lessonId = lesson.getId();

        //添加课程和班级关系
        for (int i = 0; i < classIds.length; i++) {
            lessonDao.insertRelativity(lessonId,classIds[i]);
        }

    }

    public void updateLesson(Lesson lesson) {


        lessonDao.updateByPrimaryKey(lesson);

    }

    public int deleteLesson(String lessonId) {

        try {
            lessonDao.deleteByPrimaryKey(Integer.parseInt(lessonId));
        }catch (Exception e){

            if(lessonDao.listRelativity(lessonId).size()>0){ return 1; }
            return 2;
        }
        return 0;
    }

    public void realDeleteLesson(String lessonId) {
        lessonDao.deleteAllRelativity(lessonId);
        lessonDao.deleteByPrimaryKey(Integer.parseInt(lessonId));
    }



}
