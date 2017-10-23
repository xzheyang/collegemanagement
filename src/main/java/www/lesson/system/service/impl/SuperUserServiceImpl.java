package www.lesson.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.pojo.User;
import www.lesson.shiro.utils.MD5Util;
import www.lesson.system.dao.UserDao;
import www.lesson.system.service.SuperUserService;

import javax.annotation.Resource;

@Transactional
@Service
public class SuperUserServiceImpl implements SuperUserService{

    @Resource
    UserDao userDao;


    /**
    *       由于密码加密设计问题,数据库的密码是加密的,所以修改,添加,匹配密码,都要经过加密
    **/


    public User getUserById(String userId) {
        return userDao.selectById(userId);
    }



    public boolean updateBlock(String userId, boolean is) {

        //需要修改的信息
        User user = new User();
        user.setBlock(is);
        user.setId(userId);

        //尝试修改
        try {
            userDao.updateByIdSelective(user);
        }catch (Exception e){

            return false;
        }

        return true;
    }

    public boolean updateUserPassword(User user) {

        //需要修改的信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        //修改密码需加密
        updateUser.setPassword(MD5Util.getPassword(user.getPassword(),updateUser.getSalt()));

        //尝试修改
        try {
            userDao.updateByIdSelective(user);
        }catch (Exception e){

            return false;
        }

        return true;
    }

    public boolean update(User user) {

        //尝试修改
        try {

            //这里先不考虑要修改id的问题

            if(user.getPassword()!=null){
                //修改密码需加密
                user.setPassword(MD5Util.getPassword(user.getPassword(),user.getSalt()));
            }

            userDao.updateByIdSelective(user);
        }catch (Exception e){

            return false;
        }

        return true;
    }

    public boolean insertUser(User user) {

        //尝试添加
        try {



            if(user.getPassword()!=null){
                //添加密码需加密
                user.setPassword(MD5Util.getPassword(user.getPassword(),user.getSalt()));
            }

            userDao.insertSelective(user);
        }catch (Exception e){

            return false;
        }

        return true;

    }

    public boolean deleteUser(String id) {


            userDao.deleteById(id);


        return true;
    }


}
