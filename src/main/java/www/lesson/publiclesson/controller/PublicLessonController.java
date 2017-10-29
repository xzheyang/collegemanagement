package www.lesson.publiclesson.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import www.lesson.common.utils.FileUtils;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.service.PublicLessonService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class PublicLessonController {

    @Resource(name = "publicLessonServiceImpl")
    PublicLessonService service;

    @RequiresRoles("admin")
    @RequestMapping("/admin/insertPublicLessonByExcel")
    public String insertPublicLessonByExcel(HttpServletRequest request, Model model ,
                                          @RequestParam("file")CommonsMultipartFile file) throws IOException {

        //获得上传路径
        String path=request.getSession().getServletContext().getRealPath("/upload/");

        //MultipartFile转file
        File up= FileUtils.multipartToFile(file,path);


        String errorMessage =null;
        String errorClassName =null;

        //注册
        try {
            service.insert(up);
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


        return "admin/lesson/insert_publiclesson_excel";
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/insertPublicLesson")
    public void insertPublicLesson( PublicLesson publicLesson,
                                    HttpServletResponse response) throws Exception {

        boolean success = true;

        try {
            if(publicLesson.getId()==null||publicLesson.getId().equals("")){
                service.insert(publicLesson);
            }else{
                service.insertWithId(publicLesson);
            }
            System.out.println("id:"+publicLesson.getId());

        }catch (Exception e){
            success=false;e.printStackTrace();
        }

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);
    }

    @RequiresRoles("admin")
    @RequestMapping("admin/deletePublicLesson")
    public void deletePublicLesson(@RequestParam(value = "ids", required = false) String ids,
                             HttpServletResponse response) throws Exception {

        String[] deleteIds = ids.split(",");

        JSONObject result = new JSONObject();

        try {

            for (int i = 0; i < deleteIds.length; i++) {
                //删除id
                service.delete(deleteIds[i]);

            }
            result.put("success", true);
        }catch (Exception e){
            result.put("exist", true);
        }


        ResponseUtil.write(response, result);

    }

    @RequiresRoles("admin")
    @RequestMapping("admin/updatePublicLesson")
    public void updateLesson(PublicLesson lesson, String oldId, HttpServletResponse response)throws Exception{

        boolean success = true;

        try {

            if (!oldId.equals(lesson.getId())) {

                service.updateWithId(lesson, oldId);
            } else {
                service.update(lesson);
            }
        }catch (Exception e){

            success=false;
        }



        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }

}
