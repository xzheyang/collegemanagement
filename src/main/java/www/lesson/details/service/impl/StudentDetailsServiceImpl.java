package www.lesson.details.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.details.service.StudentDetailsService;
import www.lesson.pojo.Class;
import www.lesson.pojo.Page;
import www.lesson.pojo.Student;
import www.lesson.register.dao.ClassDao;
import www.lesson.register.dao.StudentDao;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

    @Resource
    StudentDao studentDao;

    @Resource
    ClassDao classDao;

    public void listStudentsByClass(Page<Student> page, String classId) {


        //查询班级总数
        page.setTotal(studentDao.totalStudentByClass(classId));
        //结果
        page.setResult(studentDao.listStudentsByClass(classId,page.getStart(),page.getEnd()));


    }

    public Student searchStudent(String id) {
        return studentDao.selectByPrimaryKey(id);
    }


}
