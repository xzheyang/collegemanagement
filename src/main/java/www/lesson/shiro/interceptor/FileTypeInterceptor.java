package www.lesson.shiro.interceptor;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import www.lesson.common.utils.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;

public class FileTypeInterceptor extends HandlerInterceptorAdapter {

    final static String XLS_DOC="D0CF11E0";
    final static String XLSX_DOC="504B03040A";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       //判断是否是上传请求
       if(request instanceof MultipartHttpServletRequest){
           MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
           Iterator<String> it= multiRequest.getFileNames();
           while (it.hasNext()){
               MultipartFile muFile = multiRequest.getFile(it.next());
               //获取魔数type
               String type=FileUtils.getFileType(muFile);
               System.out.println("type:"+type);
               //判断上传的文件是否为Excel,
               if(!(type.startsWith(XLS_DOC)||type.startsWith(XLSX_DOC))){
                   response.sendRedirect(request.getContextPath()+"/error.jsp");
                   return false;
               }

           }
       }


       return true;
    }



}
