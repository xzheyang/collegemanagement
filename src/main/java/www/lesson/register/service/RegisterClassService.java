package www.lesson.register.service;

import www.lesson.pojo.Class;

import java.io.File;

public interface RegisterClassService {

    //使用Excel注册教师信息
    void registerClassByExcel(File file);

    //需要班级Id的接口
    void regClassByExcelNeedId(File file);

    //单独注册班级
    boolean registerClass(Class c);

}
