package www.lesson.publiclesson.filter;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import www.lesson.pojo.Page;
import www.lesson.publiclesson.controller.OpenController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenPLChoiceFilter extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!OpenController.openChoice){

            response.sendRedirect(request.getContextPath()+"/no_time.jsp");
            return false;
        }


        return true;
    }

}
