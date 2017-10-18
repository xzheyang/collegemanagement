package www.lesson.lesson.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import www.lesson.common.utils.FileUtils;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.lesson.service.ScoreCUDService;
import www.lesson.pojo.Score;
import www.lesson.pojo.ScoreKey;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class SocreCUDController {

    @Resource(name = "scoreCUDServiceImpl")
    ScoreCUDService service;

    //TODO 考虑做一个开放上传和关闭的接口

    //shiro.xml中权限设置只允许管理员或教师
    @RequestMapping("lesson/insertScoreByExcel")
    public String insertScoreByExcel(  HttpServletRequest request, Model model ,
                                        @RequestParam("file")CommonsMultipartFile file) throws IOException {

        //获得上传路径
        String path=request.getSession().getServletContext().getRealPath("/upload/");

        //MultipartFile转file
        File up= FileUtils.multipartToFile(file,path);


        String errorMessage =null;
        String errorClassName =null;

        //注册
        try {
            service.insertByExcel(up);
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
            model.addAttribute("success", "添加分数成功" );
        }


        return "teacher/score/insert_score_excel";
    }



    @RequiresRoles("admin")
    @RequestMapping("admin/insertScore")
    public void insertScore(Score score, HttpServletResponse response) throws Exception {

        boolean success = true;

        try {
            service.insert(score);
        }catch (Exception e){
            success = false;
        }

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }
    @RequiresRoles("admin")
    @RequestMapping("admin/deleteScore")
    public void deleteScore(@RequestParam(value = "lesssonIds", required = false) String lesssonIds,
                            @RequestParam(value = "studentIds", required = false) String studentIds,
                            HttpServletResponse response) throws Exception {

        String[] lessonId = lesssonIds.split(",");
        String[] studentId = studentIds.split(",");
        JSONObject result = new JSONObject();

        //TODO 以前设计的BUG,如果删除一个失败,全部不会失败,这样没有达到事务性
        try{
            for(int i=0;i<lessonId.length;i++){
                ScoreKey scoreKey = new ScoreKey();
                scoreKey.setLessonId(Integer.valueOf(lessonId[i]));
                scoreKey.setStudentId(studentId[i]);
                service.delete(scoreKey);
            }
            result.put("success", true);
        }catch (Exception e){

            result.put("exist", true);
        }

        ResponseUtil.write(response, result);
    }
    @RequiresRoles("admin")
    @RequestMapping("admin/updateScore")
    public void updateScore(Score score, HttpServletResponse response) throws Exception {

        boolean success = true;

        try {
            service.update(score);
        }catch (Exception e){
            success = false;
        }

        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

    }



}
