package www.lesson.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.lesson.pojo.Student;
import www.lesson.pojo.User;
import www.lesson.system.dao.RoleDao;
import www.lesson.system.dao.UserDao;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserRleam extends AuthorizingRealm {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    //权限授予
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = principalCollection.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;

        Set<String> roleName = new HashSet<String>();

        //这里一个数据库只有三个角色且只能有一个角色,所以比较畸形
        String roleId = userDao.selectById(userId).getRoleId();
        roleName.add(roleDao.selectById(roleId).getRole()) ;
        Set<String> permissions = new HashSet<String>();    //这里使用单角色,并先不使用permissions

        info.setRoles(roleName);
        info.setStringPermissions(permissions);

        return info;

    }

    //密码验证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username =  token.getPrincipal().toString();
        User user = userDao.selectById(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(Boolean.TRUE.equals(user.getBlock())) {
            throw new LockedAccountException(); //帐号锁定
        }

            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断,第三个是验证的盐(配置匹配器才起作用),第四个参数是Ream名。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user.getId(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),   //这里盐先定为id
                    this.getName()) ;

            return authenticationInfo ;

    }



    //清除缓存,更新权限
    public void clearCached(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
