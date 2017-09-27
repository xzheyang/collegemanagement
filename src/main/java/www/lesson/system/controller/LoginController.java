package www.lesson.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import www.lesson.pojo.User;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    /**
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginUser(){
        return "redirect:/login.jsp";
    }**/


    @RequestMapping(value = "/login")
    public String loginUser(User user, HttpServletRequest request, Model model){

        //获取错误信息,并跳转
        String error = (String) request.getAttribute("shiroLoginFailure");
        if(error!=null){
            model.addAttribute("error","密码错误");
            return "forward:login.jsp";
        }

        return "redirect:/error.jsp";
       // return "redirect:/error.jsp";
    }



    @RequestMapping(value = "/loginSuccess")
    public String loginSuccess(){

        //根据权限,跳转相应页面

        Subject subject = SecurityUtils.getSubject();

        if(subject.hasRole("student")){
            return "student/all";
        }else if(subject.hasRole("teacher")){
            return "teacher/all";
        }else if(subject.hasRole("admin")){
            return "admin/all";
        }

        return "redirect:/error.jsp";
    }



}
