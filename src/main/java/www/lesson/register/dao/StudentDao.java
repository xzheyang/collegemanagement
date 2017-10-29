package www.lesson.register.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import www.lesson.pojo.Student;

import java.util.List;

@Repository
public interface StudentDao {

    int deleteByPrimaryKey(String id);

    boolean insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //显示注册前面的学生Id
    List<String> listPreStudentIds(String classId);
    //按班级显示学生数据,分页          注意:Limit语句不能用字符
    List<Student> listStudentsByClass(@Param("classId") String classId,
                                      @Param("start")int start ,@Param("end") int end);
    //显示此班级的总数
    int totalStudentByClass(String classId);
    //总共有哪些班级Id
    List<String> listClass();

    //获得学生名
    String getNameById(String id);

    //获得班级Id
    String getClassIdById(String id);
}
