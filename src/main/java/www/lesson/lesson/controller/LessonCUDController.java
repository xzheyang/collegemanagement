package www.lesson.lesson.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import www.lesson.common.utils.FileUtils;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.lesson.service.LessonCUDService;
import www.lesson.pojo.Lesson;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequiresRoles("admin")
public class LessonCUDController {
    @Resource(name = "lessonCUDServiceImpl")
    LessonCUDService service;


    @RequestMapping("admin/insertLessonByExcel")
    public String insertLessonByExcel(
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
            service.insertLessons(up);
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
            model.addAttribute("success", "加入课程成功" );
        }


        return "admin/lesson/insert_lesson_excel";
    }


    @RequestMapping("admin/insertLesson")
    public void insertLesson(Lesson lesson,String classIds,
                             HttpServletResponse response) throws Exception {

        String[] ids = classIds.split(",");

        boolean success = true;

        try {
            service.insertLesson(lesson,ids);
        }catch (Exception e){
            success=false;
        }

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }


    @RequestMapping("admin/deleteLesson")
    public void deleteLesson(@RequestParam(value = "ids", required = false) String ids,
                             HttpServletResponse response) throws Exception {

        String[] deleteIds = ids.split(",");

        JSONObject result = new JSONObject();

        try {

            for (int i = 0; i < deleteIds.length; i++) {
                //删除id
                service.realDeleteLesson(deleteIds[i]);

            }
            result.put("success", true);
        }catch (Exception e){
            //课程下有分数
            result.put("exist", true);
        }


        ResponseUtil.write(response, result);

    }


    @RequestMapping("admin/updateLesson")
    public void updateLesson(Lesson lesson, String oldId, HttpServletResponse response)throws Exception{

        if(!oldId.equals(lesson.getId())){

            //TODO 以后添加更改主键服务
        }

        boolean success = true;

        try {
        service.updateLesson(lesson);
        }catch (Exception e){
            success=false;
        }

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }




}
