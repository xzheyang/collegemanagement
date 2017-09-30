package www.lesson.register.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import www.lesson.common.utils.FileUtils;
import www.lesson.pojo.Class;
import www.lesson.register.service.RegisterClassService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@RequiresRoles("admin")
@Controller
public class RegisterClassController {

    @Resource(name = "registerClassServiceImpl")
    RegisterClassService service;

    @RequestMapping("admin/registerClassExcel")
    public String registerClassByExcel(
            HttpServletRequest request, Model model ,
            @RequestParam("file")CommonsMultipartFile file) throws IOException {

        //获得上传路径
        String path=request.getSession().getServletContext().getRealPath("/upload/");

        //MultipartFile转file
        File up= FileUtils.multipartToFile(file,path);


        String errorMessage =null;
        String errorClassName =null;

        //注册
        try {
            service.registerClassByExcel(up);
        }catch (Exception e){
            errorMessage = e.getMessage();
            errorClassName = e.getClass().getName();
        }

        //返回错误信息
        if(errorClassName!=null){
            if(RuntimeException.class.getName().equals(errorClassName)){
                model.addAttribute("error", errorMessage );
            }else {
                model.addAttribute("error", errorMessage );
                System.out.println("错误");
            }
        }else{
            model.addAttribute("success", "注册成功" );
        }

        return "admin/register/register_class_excel";
    }

    @RequestMapping("admin/registerClass")
    public String registerClass(Class c){

        boolean result = service.registerClass(c);

        return null;
    }

}
