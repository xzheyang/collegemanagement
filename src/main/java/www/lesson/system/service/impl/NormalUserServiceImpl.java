package www.lesson.system.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import www.lesson.pojo.User;
import www.lesson.shiro.utils.MD5Util;
import www.lesson.system.dao.UserDao;
import www.lesson.system.service.NormalUserService;
import javax.annotation.Resource;



@Service
public class NormalUserServiceImpl implements NormalUserService {

    @Resource
    UserDao userDao;


    /**
     *       由于密码加密设计问题,数据库的密码是加密的,所以修改,添加,匹配密码,都要经过加密
     **/


    public Boolean updatePassword(String oldPassword, String newPassword) {

        //获得本用户的userId
        Subject subject = SecurityUtils.getSubject();
        String userId = (String)subject.getPrincipal();



        //获得原来密码
        User user = userDao.selectById(userId);


        //将验证密码加密
        oldPassword = MD5Util.getPassword(oldPassword,user.getSalt());


        //密码匹配则修改密码
        if(oldPassword.equals(user.getPassword())){

            User updateUser = new User();
            updateUser.setId(userId);

            //将新密码加密
            newPassword = MD5Util.getPassword(newPassword,updateUser.getSalt());


            updateUser.setPassword(newPassword);
            userDao.updateByIdSelective(updateUser);

            return true;
        }

        return false;
    }


}