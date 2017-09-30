package www.lesson.register.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import www.lesson.common.utils.FileUtils;
import www.lesson.pojo.Student;
import www.lesson.register.service.RegisterStudentService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RequiresRoles("admin")
@Controller
public class RegisterStudentController {
    @Resource(name = "registerStudentServiceImpl")
    RegisterStudentService service;

    @RequestMapping("admin/registerStudentExcel")
    public String registerStudentByExcel(
                        HttpServletRequest request, Model model,
                         @RequestParam("file")CommonsMultipartFile file) throws IOException {

        //获得上传路径
        String path=request.getSession().getServletContext().getRealPath("/upload/");

        //MultipartFile转file
        File up= FileUtils.multipartToFile(file,path);

        String errorMessage =null;
        String errorClassName =null;

        //注册
        try {
            service.registerStudentByExcel(up);
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
            }
        }else{
            model.addAttribute("success", "注册成功" );
        }


        return "admin/register/register_student_excel";
    }

    @RequestMapping("admin/registerStudent")
    public String registerStudent(Student student){

        boolean result = service.registerStudent(student);

        return null;
    }


}
