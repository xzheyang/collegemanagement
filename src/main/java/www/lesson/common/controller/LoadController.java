package www.lesson.common.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *          此控制器负责lesson的easy-ui页面跳转
 *
 */


@Controller
public class LoadController {


    //管理员跳转控制
    @RequiresRoles("admin")
    @RequestMapping(value="/admin/{path1:\\w+}/{path2:\\w+}")
    public String adminLoad(@PathVariable String path1,@PathVariable String path2){
        return "admin/"+path1+"/"+path2;
    }

    //通常user跳转控制
    @RequiresAuthentication
    @RequestMapping(value="/user/{path1:\\w+}")
    public String userLoad(@PathVariable String path1){
        return "user/"+path1;
    }




}
