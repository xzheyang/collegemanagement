package www.lesson.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.pojo.Page;
import www.lesson.pojo.User;
import www.lesson.system.service.SuperUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//管理员用的userController
@Controller
public class SuperUserController {

    @Resource(name = "superUserServiceImpl")
    SuperUserService service;

    //搜索用户
    @RequiresRoles("admin")
    @RequestMapping("/admin/searchUser")
    public String searchUser(@RequestParam(value = "page", required = false) String page,  //当前多少页
                             @RequestParam(value = "rows", required = false) String rows,  //有几行
                             String searchType, String searchValue,
                             HttpServletResponse response) throws Exception {


        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();



        if ("name".equals(searchType)) {


            //定义分页bean
            Page<User> pageBean = new Page<User>(Integer.parseInt(page)
                    , Integer.parseInt(rows));


            //拿到分页结果已经记录总数的pageBean
            // pageBean = Service.listByName(name,Page);



            //通过fastJson序列化list为jsonArray
            String jsonArray = JSON.toJSONString(pageBean.getResult());
            JSONArray array = JSONArray.parseArray(jsonArray);
            //将序列化结果放入json对象中
            result.put("rows", array);
            result.put("total", pageBean.getTotal());




        }else if("id".equals(searchType)){



            User user = service.getUserById(searchValue);
            List<User> list = new ArrayList<User>();
            list.add(user);

            //通过fastJson序列化list为jsonArray
            String jsonArray = JSON.toJSONString(list);
            JSONArray array = JSONArray.parseArray(jsonArray);


            //添加数据
            result.put("rows", array);
            result.put("total", 1);

        }

        //自定义写入数据
        ResponseUtil.write(response, result);

        return null;
    }


    //修改User
    @RequiresRoles("admin")
    @RequestMapping("/admin/updateUser")
    public String updateUser(User user,String oldId, HttpServletResponse response)throws Exception{

        if(!oldId.equals(user.getId())){
            System.out.println("需要修改id");
            //TODO 以后添加更改Id服务
        }

        boolean success = service.update(user);


        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);

        return null;
    }

    //添加User
    @RequiresRoles("admin")
    @RequestMapping("/admin/insertUser")
    public String insertUser(User user, HttpServletResponse response)throws Exception{

        boolean success = service.insertUser(user);


        //返回信息
        JSONObject result = new JSONObject();
        result.put("success", success);
        ResponseUtil.write(response, result);



        return null;
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/deleteUser")
    public void deleteUser(@RequestParam(value = "ids", required = false) String ids,
                             HttpServletResponse response) throws Exception {

        //解析数据
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();

        try {

            for (int i = 0; i < idsStr.length; i++) {
                //删除id
                boolean b = service.deleteUser(idsStr[i]);

            }

            result.put("success", true);
        }catch (Exception e){
            //失败一般是由于user有详细信息,或已删除
            result.put("exist", true);
        }


        ResponseUtil.write(response, result);

    }





}

