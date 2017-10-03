package www.lesson.register.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import www.lesson.pojo.Class;
import java.util.List;

@Repository
public interface ClassDao {

    int deleteByPrimaryKey(String id);

    boolean insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    //查询最后一条id值(注意mysql用的是首字母排序),舍弃
    String selectMaxId();

    //查询同一学年的班级号
    List<String> listPreClassIds(String year);

    //查询此学年班级数量
    int totalClassByYear(String year);

    //查询学年的所有班级,分页
    List<Class> listClassByYear(@Param("year") String year,
                                @Param("start") int start, @Param("end") int end);

    //查询所有年级
    List<String> listYears();

    //查询所有班级
    List<Class> listClass();

}
