package www.lesson.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import www.lesson.pojo.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ResponseUtil {
    /**
     * 向response对象写入数据
     * @param response
     * @param obj
     * @throws Exception
     */
    public static void write(HttpServletResponse response, Object obj)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(obj.toString());
        out.flush();
        out.close();
    }

    //easy ui分页输出
    public static void pageWrite(HttpServletResponse response, Page page)throws Exception{

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();

        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(page.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        //将序列化结果放入json对象中
        result.put("rows", array);
        result.put("total", page.getTotal());
        //自定义写入数据
        ResponseUtil.write(response, result);

    }

    //下载
    public static void down(HttpServletResponse response,File file,String fileName) throws IOException {

        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(file));
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();

    }

}
