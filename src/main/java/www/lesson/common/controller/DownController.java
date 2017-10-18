package www.lesson.common.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



/**
 *          此控制器负责下载
 *
 */

@Controller
public class DownController {


    @RequestMapping(value="/down/myWebGuide")
    public void downGuide(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fileName = request.getSession().getServletContext().getRealPath("down")+"/myweb.txt";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));

        String downFileName = "myweb.txt";

        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + downFileName);
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

    //Excel模版下载
    @RequestMapping(value="/down/example/{path:\\w+}")
    public void userLoad(HttpServletRequest request, HttpServletResponse response,@PathVariable String path) throws IOException {

        String fileName = request.getSession().getServletContext().getRealPath("down/example/")+path+".xlsx";
        //获取输入流


        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //视图错误用全局springmvc异常处理

        String downFileName = path+".xlsx";

        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + downFileName);
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
