package www.lesson.publiclesson.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.pojo.PublicLesson;
import www.lesson.publiclesson.service.ChoicePublicLessonService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChoicePublicLessonController {

    @Resource(name = "choicePublicLessonServiceImpl")
    ChoicePublicLessonService service;

    @RequiresRoles("student")
    @RequestMapping("/publicLesson/insertChoice")
    public void insertPublicLessonChoice(String lessonId, HttpSession session , HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        //获得自己的学号
        String studentId = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));

        try{
            service.insertChoice(studentId,lessonId);
            result.put("success", true);
        }catch (RuntimeException e){
            result.put("error", e.getMessage());
        }catch (Exception e){
            result.put("error", "未知异常");
        }


        //返回信息
        ResponseUtil.write(response, result);
    }

    @RequiresRoles("student")
    @RequestMapping("/publicLesson/deleteChoice")
    public void deletePublicLessonChoice(String lessonId, HttpSession session , HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        //获得自己的学号
        String studentId = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));

        try{
            service.deleteChoice(studentId,lessonId);
            result.put("success", true);
        }catch (RuntimeException e){
            result.put("error", e.getMessage());
        }catch (Exception e){
            result.put("error", "未知异常");
        }

        //返回信息
        ResponseUtil.write(response, result);
    }

    @RequiresRoles("student")
    @RequestMapping("/publicLesson/getChoice")
    public void getPublicLessonChoice(String lessonId, HttpSession session , HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        //获得自己的学号
        String studentId = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));

        PublicLesson choiceLesson = service.getChoice(studentId);

        //格式
        List<PublicLesson> show = new ArrayList<PublicLesson>();
        show.add(choiceLesson);
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(show);
        JSONArray array = JSONArray.parseArray(jsonArray);
        //添加数据
        result.put("rows", array);
        result.put("total", 1);
        //返回信息
        ResponseUtil.write(response, result);
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/downPLExcels")
    public void downPLExcels(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String filePath = request.getServletContext().getRealPath("down/publiclessonchoice");
        File file = new File(filePath,"allPublicLessonChocies.xlsx");
        service.listChoice(file);
        ResponseUtil.down(response,file,"allPublicLessonChocies.xlsx");
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/downPLExcelByLessonId")
    public void downPLExcelByLessonId(HttpServletRequest request,HttpServletResponse response,String publicLessonId) throws IOException {
        String filePath = request.getServletContext().getRealPath("down/publiclessonchoice");
        File file = new File(filePath,"publicLessonChocie"+publicLessonId+".xlsx");
        service.listChoiceByLessonId(publicLessonId,file);
        ResponseUtil.down(response,file,"publicLessonChocie"+publicLessonId+".xlsx");
    }

}
