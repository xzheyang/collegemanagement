package www.lesson.details.service.impl;


import org.springframework.stereotype.Service;
import www.lesson.details.service.ClassDetailsService;
import www.lesson.pojo.Class;
import www.lesson.pojo.Page;
import www.lesson.register.dao.ClassDao;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClassDetailsServiceImpl implements ClassDetailsService {

    @Resource
    ClassDao classDao;

    public void listClassByYear(Page<Class> page, String year) {


        //设置此年班级总数
        page.setTotal(classDao.totalClassByYear(year));
        //查询结果
        page.setResult(classDao.listClassByYear(year,page.getStart(),page.getEnd()));


    }



    public List<String> listYears() {

        List<String> years= classDao.listYears();

        Set set = new HashSet(years);

        years.clear();

        years.addAll(set);


        return years;
    }

    public List<Class> listClass() {

        return classDao.listClass();
    }

    public Class searchClass(String id) {
        return classDao.selectByPrimaryKey(id);
    }


}
