package www.lesson.system.service;


import www.lesson.pojo.Page;
import www.lesson.pojo.User;


//管理员专用的User操作        这里删除修改撤回, crud关联详细信息待完成
public interface SuperUserService {



    //  通过Id搜索用户  以后可以扩展为查询详细信息
    User getUserById(String userId);

    //  通过name搜索
    //Page getUserByName(String name);


    //将用户锁定
    boolean updateBlock(String userId,boolean is);


    //修改用户密码
    boolean updateUserPassword(User user);


    //修改User里有的信息   如果改id则将值回复到默认
    boolean update(User user);


    //这里的添加用户,只是User帐号,并未关联student,teacher数据,所以搁置
    boolean insertUser(User user);

    //通过id删除user
    boolean deleteUser(String id);


}
