package www.lesson.details.service;

import www.lesson.pojo.Class;
import www.lesson.pojo.Page;

public interface ClassDetails {

    //分页显示此学年数据
    Page<Class> listClassByYear(Page page,String Year);

}
