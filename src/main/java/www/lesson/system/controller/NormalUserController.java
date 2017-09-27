package www.lesson.system.controller;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import www.lesson.system.service.NormalUserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//普通用户用的userController
@Controller
public class NormalUserController {

    @Resource(name = "normalUserServiceImpl")
    NormalUserService service;


    @RequiresAuthentication
    @RequestMapping("/user/updatePassword")
    @ResponseBody
    public Map updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                              @RequestParam(value = "newPassword") String newPassword) {



        boolean result = service.updatePassword(oldPassword,newPassword);

        System.out.println(result);

        Map flag = new HashMap();
        flag.put("data",1);

        return flag;
    }

}
