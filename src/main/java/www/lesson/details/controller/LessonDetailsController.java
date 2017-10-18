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
import www.lesson.details.service.LessonDetailsService;
import www.lesson.pojo.Lesson;
import www.lesson.pojo.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LessonDetailsController {

    @Resource(name = "lessonDetailsServiceImpl")
    LessonDetailsService service;

    @RequestMapping("/details/listLessonByClass")
    public void listLessonByClass(@RequestParam(value = "page", required = false) String page,  //当前多少页
                                @RequestParam(value = "rows", required = false) String rows,  //有几行
                                String byClass , HttpServletResponse response) throws Exception {




        //传输前端页面数据
        Page<Lesson> pageBean = new Page<Lesson>(Integer.parseInt(page), Integer.parseInt(rows));
        //查询数据
        service.listLessonsByClass(pageBean,byClass);


        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();

        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        //将序列化结果放入json对象中
        result.put("rows", array);
        result.put("total", pageBean.getTotal());
        //自定义写入数据
        ResponseUtil.write(response, result);

    }


    @RequiresRoles("teacher")
    @RequestMapping("/details/listLessonByTeacher")
    public void listClass(HttpServletResponse response, HttpSession session) throws Exception {

        String teacherId = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));

        List<Lesson> lessons = service.listLessonsByTeacher(teacherId);


        String jsonArray = JSON.toJSONString(lessons);
        JSONArray array = JSONArray.parseArray(jsonArray);

        //自定义写入数据
        ResponseUtil.write(response, array);

    }


}
