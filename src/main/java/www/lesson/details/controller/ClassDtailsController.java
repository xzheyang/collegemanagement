package www.lesson.details.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.details.service.ClassDetailsService;
import www.lesson.pojo.Class;
import www.lesson.pojo.Page;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ClassDtailsController {

    /**
     *  Class数据显示和数据访问Class信息的控制器
     *
     */


    @Resource(name = "classDetailsServiceImpl")
    ClassDetailsService service;


    @RequestMapping("/details/listClassByYear")
    public void listClassByYear(@RequestParam(value = "page", required = false) String page,  //当前多少页
                             @RequestParam(value = "rows", required = false) String rows,  //有几行
                              String  byYear,HttpServletResponse response) throws Exception {


        //传输前端页面数据
        Page<Class> pageBean = new Page<Class>(Integer.parseInt(page), Integer.parseInt(rows));
        //查询数据
        service.listClassByYear(pageBean,byYear);


        ResponseUtil.pageWrite(response,pageBean);


    }



    @RequestMapping("/details/listYears")
    public void listClassByYear(HttpServletResponse response) throws Exception {


        List<String> list = service.listYears();

        List<Class> result = new ArrayList<Class>();

        //TODO 因为组合框需要vo但这里只需要year一样属性所以先用pojo代替
        for(String year: list){
            Class c = new Class();
            c.setYear(year);
            result.add(c);
        }


        String jsonArray = JSON.toJSONString(result);
        JSONArray array = JSONArray.parseArray(jsonArray);



        //自定义写入数据
        ResponseUtil.write(response, array);

        //response.getWriter().write(JSONArray.toJSONString(result));
    }


    @RequestMapping("/details/listClass")
    public void listClass(HttpServletResponse response) throws Exception {

        List<Class> classes = service.listClass();


        String jsonArray = JSON.toJSONString(classes);
        JSONArray array = JSONArray.parseArray(jsonArray);

        //自定义写入数据
        ResponseUtil.write(response, array);

    }


    @RequestMapping("/details/searchClassById")
    public void searchClassById(@RequestParam(value = "page", required = false) String page,  //当前多少页
                            @RequestParam(value = "rows", required = false) String rows,  //有几行
                                String  searchType,String  searchValue,HttpServletResponse response) throws Exception {

       Class c = service.searchClass(searchValue);
       List<Class> lc = new ArrayList<Class>();
       lc.add(c);


        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(lc);
        JSONArray array = JSONArray.parseArray(jsonArray);
        //添加数据
        result.put("rows", array);
        result.put("total", 1);
        //自定义写入数据
        ResponseUtil.write(response, result);

    }



}
