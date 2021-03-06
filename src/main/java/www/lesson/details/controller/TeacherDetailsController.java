package www.lesson.details.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.details.service.TeacherDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.Teacher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherDetailsController {

    /**
     *  Teacher数据显示和数据访问Teacher息的控制器
     *
     */

    @Resource(name = "teacherDetailsServiceImpl")
    TeacherDetailsService service;

    @RequiresRoles("admin")
    @RequestMapping("/details/listTeachers")
    public void teacher(@RequestParam(value = "page", required = false) String page,  //当前多少页
                        @RequestParam(value = "rows", required = false) String rows,  //有几行
                          HttpServletResponse response) throws Exception {


        //传输前端页面数据
        Page<Teacher> pageBean = new Page<Teacher>(Integer.parseInt(page),Integer.parseInt(rows));
        //查询数据
        service.listTeachers(pageBean);

        ResponseUtil.pageWrite(response,pageBean);

    }

    @RequiresRoles("admin")
    @RequestMapping("/details/searchTeacherById")
    public void searchTeacher(@RequestParam(value = "page", required = false) String page,  //当前多少页
                              @RequestParam(value = "rows", required = false) String rows,  //有几行
                              String  searchType,String  searchValue,HttpServletResponse response) throws Exception {

        Teacher teacher = service.searchTeacher(searchValue);
        List<Teacher> lt = new ArrayList<Teacher>();
        lt.add(teacher);

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(lt);
        JSONArray array = JSONArray.parseArray(jsonArray);
        //添加数据
        result.put("rows", array);
        result.put("total", 1);
        //自定义写入数据
        ResponseUtil.write(response, result);


    }



}
