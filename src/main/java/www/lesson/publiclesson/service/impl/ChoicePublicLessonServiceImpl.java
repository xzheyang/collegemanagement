package www.lesson.publiclesson.service.impl;

import org.springframework.stereotype.Service;
import www.lesson.common.utils.ExcelUtils;
import www.lesson.details.service.StudentDetailsService;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.dao.ChoicePublicLessonDao;
import www.lesson.publiclesson.dao.PublicLessonDao;
import www.lesson.publiclesson.service.ChoicePublicLessonService;
import www.lesson.vo.StudentSimpleVO;


import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Service
public class ChoicePublicLessonServiceImpl implements ChoicePublicLessonService {

    @Resource(name = "redisChoicePublicLessonDao")
    ChoicePublicLessonDao choiceDao;

    @Resource(name = "redisPublicLessonDao")
    PublicLessonDao lessonDao;

    @Resource(name = "studentDetailsServiceImpl")
    StudentDetailsService studentVOService;


    public void insertChoice(String studentId, String lessonId) {

        choiceDao.addChoice(studentId,lessonId);

    }


    public void deleteChoice(String studentId, String lessonId) {

        choiceDao.deleteChoice(studentId,lessonId);

    }

    public void listChoice(File file) {
       Map<String,List<String>> allChoice = choiceDao.listChoice();
       //查询结果
       Map<String,List> result = new HashMap<String, List>();

       for(String lessonId:allChoice.keySet()){
          List<String> studentIds = allChoice.get(lessonId);//获得studentId
          List<StudentSimpleVO> studentVO = new ArrayList<StudentSimpleVO>();

            for (int i=0;i<studentIds.size();i++){
                StudentSimpleVO details= studentVOService.searchStudentSimpleVO(studentIds.get(i));
                studentVO.add(details);
            }
           result.put(lessonId,studentVO);//将每门公选课学生信息分别放入
       }

       ExcelUtils.downExcel(result,file);


    }

    public void listChoiceByLessonId(String lessonId,File file) {
        Set<String> studentIds = choiceDao.listChoiceByLessonId(lessonId);
        Map<String,List> result = new HashMap<String, List>();
        List<StudentSimpleVO> students = new ArrayList<StudentSimpleVO>();

        for(String studentId : studentIds){
            StudentSimpleVO details= studentVOService.searchStudentSimpleVO(studentId);
            students.add(details);
        }

        result.put(lessonId,students);  //将公选课学生信息放入

        ExcelUtils.downExcel(result,file);

    }

    public PublicLesson getChoice(String studentId) {

        String lessonId = choiceDao.getChoice(studentId);

        if(lessonId!=null){
            PublicLesson publicLesson = lessonDao.getPublicLesson(lessonId);
            //查询在线人数 没有VO将就用teacherId
            publicLesson.setTeacherId(String.valueOf(choiceDao.getChoiceCount(lessonId)));
            return publicLesson;
        }else{
            return null;
        }

    }


}
