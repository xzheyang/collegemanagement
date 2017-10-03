package www.lesson.register.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import www.lesson.common.utils.FileUtils;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.pojo.Student;
import www.lesson.register.service.RegisterStudentService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


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
    public String registerStudent(Student student , Model model){

        boolean result = service.registerStudent(student);

        //返回结果
        if(result){
            model.addAttribute("success","注册成功");
        }else{
            model.addAttribute("success","注册失败");
        }

        return "admin/register/register_student";
    }


    //能处理ajax请求的单独注册
    @RequestMapping("admin/insertStudent")
    public void insertStudent(Student student , HttpServletResponse response) throws Exception {

        //TODO 这里的数据是自动生成Id的,要么前端后端设置为空就自动生成,要么前端改为不能设置Id
        boolean success = service.registerStudent(student);

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }

    @RequestMapping("/admin/deleteStudent")
    public void deleteStudent(@RequestParam(value = "ids", required = false) String ids,
                           HttpServletResponse response) throws Exception {

        //解析数据
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();

        try {

            for (int i = 0; i < idsStr.length; i++) {
                //删除id
                service.deleteStudent(idsStr[i]);

            }

            result.put("success", true);
        }catch (Exception e){
            //学生下有分数
            result.put("exist", true);
        }


        ResponseUtil.write(response, result);

    }


    @RequestMapping("/admin/updateStudent")
    public void updateStudent(Student student, String oldId, HttpServletResponse response)throws Exception{

        if(!oldId.equals(student.getId())){
            System.out.println("需要修改id");
            //TODO 以后添加更改主键服务
        }


        boolean success = service.updateStudent(student);

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);


    }


}
