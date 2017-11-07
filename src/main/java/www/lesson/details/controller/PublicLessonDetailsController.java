package www.lesson.details.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.details.service.PublicLessonDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.PublicLesson;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PublicLessonDetailsController {

    @Resource(name = "publicLessonDetailsServiceImpl")
    PublicLessonDetailsService service;


    @RequestMapping("/details/listPublicLesson")
    @ResponseBody
    public Map listPublicLesson(@RequestParam(value = "page", required = false) String page,  //当前多少页
                                @RequestParam(value = "rows", required = false) String rows  //有几行
                                ) throws Exception {

        Page<PublicLesson> pageBean = new Page<PublicLesson>(Integer.parseInt(page), Integer.parseInt(rows));

        service.listPublicLesson(pageBean);

        Map result = new HashMap();
        result.put("rows", pageBean.getResult());
        result.put("total", pageBean.getTotal());


        return result;
    }

    @RequestMapping("/details/listPublicLessonForS")
    @ResponseBody
    public Map listPublicLessonForS(  @RequestParam(value = "page", required = false) String page,  //当前多少页
                                   @RequestParam(value = "rows", required = false) String rows  //有几行
                                   ) throws Exception {

        Page<PublicLesson> pageBean = new Page<PublicLesson>(Integer.parseInt(page), Integer.parseInt(rows));

        service.listPublicLessonForS(pageBean);

        Map result = new HashMap();
        result.put("rows", pageBean.getResult());
        result.put("total", pageBean.getTotal());


        return result;

    }


}
