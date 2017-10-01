package www.lesson.details.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.details.service.ClassDetails;
import www.lesson.pojo.Class;
import www.lesson.pojo.Page;

@Service
@Transactional
public class ClassDetailsImpl implements ClassDetails {



    public Page<Class> listClassByYear(Page page, String Year) {



        return null;
    }

}
