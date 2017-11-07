package www.lesson.publiclesson.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OpenController {
    public static boolean openChoice=false;

    @RequiresRoles("admin")
    @RequestMapping("/admin/changePLChoice")
    @ResponseBody
    public Map<String,Boolean> changeChoice(String open,HttpServletResponse response) throws Exception {

        if("true".equals(open)){
            openChoice=true;
        } else if ("false".equals(open)) {
            openChoice=false;
        }

        Map<String,Boolean> result = new HashMap<String, Boolean>();
        result.put("open",openChoice);

        return result;
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/showPLChoice")
    @ResponseBody
    public Map<String,Boolean> showChoice(HttpServletResponse response) throws Exception {
        /*JSONObject result = new JSONObject();
        result.put("open", openChoice);
        ResponseUtil.write(response, result);*/
        Map<String,Boolean> result = new HashMap<String, Boolean>();
        result.put("open",openChoice);

        return result;
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/openPLChoiceByTime")
    public void openChoiceByTime(){

    }

}
