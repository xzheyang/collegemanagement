package www.lesson.details.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.details.service.StudentDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.Student;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentDetailsController {

    /**
     *  Student数据显示和数据访问Student信息的控制器
     *
     */

    @Resource(name = "studentDetailsServiceImpl")
    StudentDetailsService service;

    @RequestMapping("/details/listStudentByClass")
    public void listStudentByYear(@RequestParam(value = "page", required = false) String page,  //当前多少页
                                  @RequestParam(value = "rows", required = false) String rows,  //有几行
                                  String byClass , HttpServletResponse response) throws Exception {




        //传输前端页面数据
        Page<Student> pageBean = new Page<Student>(Integer.parseInt(page), Integer.parseInt(rows));
        //查询数据
        service.listStudentsByClass(pageBean,byClass);


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


    @RequestMapping("/details/searchStudentById")
    public void searchStudentByClass(@RequestParam(value = "page", required = false) String page,  //当前多少页
                                  @RequestParam(value = "rows", required = false) String rows,  //有几行
                                  String  searchType,String  searchValue,HttpServletResponse response) throws Exception {

        Student student = service.searchStudent(searchValue);
        List<Student> ls = new ArrayList<Student>();
        ls.add(student);

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(ls);
        JSONArray array = JSONArray.parseArray(jsonArray);
        //添加数据
        result.put("rows", array);
        result.put("total", 1);
        //自定义写入数据
        ResponseUtil.write(response, result);

    }





}
