package www.lesson.details.service;

import www.lesson.pojo.Class;
import www.lesson.pojo.Page;

import java.util.List;

public interface ClassDetailsService {

    //分页显示此学年数据
    void listClassByYear(Page<Class> page,String year);

    //显示所有年级
    List<String> listYears();

    //显示所有班级
    List<Class> listClass();

    //搜索班级通过Id
    Class searchClass(String id);

}
