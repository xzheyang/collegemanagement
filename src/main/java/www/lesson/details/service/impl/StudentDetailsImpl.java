package www.lesson.details.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.details.service.StudentDetails;
import www.lesson.pojo.Page;
import www.lesson.pojo.Student;
import www.lesson.register.dao.StudentDao;

import javax.annotation.Resource;

@Service
@Transactional
public class StudentDetailsImpl implements StudentDetails {

    @Resource
    StudentDao studentDao;

    public Page<Student> listStudentsByClass(Page<Student> page, String classId) {

        //返回一个新的Page,防止数据出错
        Page<Student> result = new Page<Student>();

        String start = String.valueOf(page.getStart());
        String end = String.valueOf(page.getEnd());

        //查询班级总数
        int total = studentDao.totalStudentByClass(classId);
        result.setTotal(total);
        //结果
        result.setResult(studentDao.listStudentsByClass(classId,start,end));


        return result;
    }



}
