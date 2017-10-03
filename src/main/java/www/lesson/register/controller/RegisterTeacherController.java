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
import www.lesson.pojo.Teacher;
import www.lesson.register.service.RegisterTeacherService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@RequiresRoles("admin")
@Controller
public class RegisterTeacherController {

    @Resource(name = "registerTeacherServiceImpl")
    RegisterTeacherService service;

    @RequestMapping("admin/registerTeacherExcel")
    public String registerTeacherByExcel(
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
            service.registerTeacherByExcel(up);
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





        return "admin/register/register_teacher_excel";

    }

    @RequestMapping("admin/registerTeacher")
    public String registerTeacher(Teacher teacher , Model model){

        boolean result = service.registerTeacher(teacher);

        //返回结果
        if(result){
            model.addAttribute("success","注册成功");
        }else{
            model.addAttribute("success","注册失败");
        }

        return "admin/register/register_teacher";
    }

    //能处理ajax请求的单独注册
    @RequestMapping("admin/insertTeacher")
    public void insertTeacher(Teacher teacher , HttpServletResponse response) throws Exception {

        boolean success = service.registerTeacher(teacher);

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }

    @RequestMapping("/admin/deleteTeacher")
    public void deleteTeacher(@RequestParam(value = "ids", required = false) String ids,
                              HttpServletResponse response) throws Exception {

        //解析数据
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();

        try {

            for (int i = 0; i < idsStr.length; i++) {
                //删除id
                service.deleteTeacher(idsStr[i]);

            }

            result.put("success", true);
        }catch (Exception e){
            //教师下有课程,班级
            result.put("exist", true);
        }


        ResponseUtil.write(response, result);

    }

    @RequestMapping("/admin/updateTeacher")
    public void updateTeacher(Teacher teacher, String oldId, HttpServletResponse response)throws Exception{

        if(!oldId.equals(teacher.getId())){
            System.out.println("需要修改id");
            //TODO 以后添加更改主键服务
        }


        boolean success = service.updateTeacher(teacher);

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);


    }


}
