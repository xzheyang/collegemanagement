package www.lesson.system.service;


//老师和学生帐号基本操作
public interface NormalUserService {

    //修改自己密码,并确认原来的密码
    Boolean updatePassword(String oldPassword,String newPassword);


}
