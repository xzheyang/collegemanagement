package www.lesson.details.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.details.service.ScoreDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.Score;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class ScoreDetailsController {

    @Resource(name = "scoreDetailsServiceImpl")
    ScoreDetailsService service;

    @RequiresRoles("student")
    @RequestMapping("/details/listScoreByStudentId")
    public void listScoreByStudentId(@RequestParam(value = "page", required = false) String page,  //当前多少页
                                     @RequestParam(value = "rows", required = false) String rows,  //有几行
                                     HttpServletResponse response, HttpSession session) throws Exception {

        //传输前端页面数据
        Page<Score> pageBean = new Page<Score>(Integer.parseInt(page), Integer.parseInt(rows));

        //获得自己的学号
        String studentId = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
        service.listScoreByStudentId(pageBean,studentId);



        ResponseUtil.pageWrite(response,pageBean);

    }

    //spring-shiro.xml中配置(教师,管理员允许)
    @RequestMapping("/details/listScoreByLessonId")
    public void listScoreByLessonId(@RequestParam(value = "page", required = false) String page,  //当前多少页
                                     @RequestParam(value = "rows", required = false) String rows,  //有几行
                                     String byLesson , HttpServletResponse response) throws Exception {

        //传输前端页面数据
        Page<Score> pageBean = new Page<Score>(Integer.parseInt(page), Integer.parseInt(rows));


        service.listScoreByLessonId(pageBean,byLesson);


        ResponseUtil.pageWrite(response,pageBean);


    }



}
