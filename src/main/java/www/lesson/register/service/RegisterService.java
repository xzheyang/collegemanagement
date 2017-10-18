package www.lesson.register.service;


import java.io.File;

public interface RegisterService<T> {
    /**
    *           学生,老师和班级的注册接口
     **/

    //通过Excel注册
    void registerByExcel(File file);

    //单个注册
    boolean register(T t);

    //意外修改
    boolean update(T t);

    //意外删除  老师学生会删除user
    boolean delete(String id);

}
