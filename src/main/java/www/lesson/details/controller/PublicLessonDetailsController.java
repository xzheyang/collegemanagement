package www.lesson.details.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.lesson.common.utils.ResponseUtil;
import www.lesson.details.service.PublicLessonDetailsService;
import www.lesson.pojo.Page;
import www.lesson.pojo.PublicLesson;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublicLessonDetailsController {

    @Resource(name = "publicLessonDetailsServiceImpl")
    PublicLessonDetailsService service;


    @RequestMapping("/details/listPublicLesson")
    public void listPublicLesson(  @RequestParam(value = "page", required = false) String page,  //当前多少页
                                   @RequestParam(value = "rows", required = false) String rows,  //有几行
                                   HttpServletResponse response   ) throws Exception {

        Page<PublicLesson> pageBean = new Page<PublicLesson>(Integer.parseInt(page), Integer.parseInt(rows));

        service.listPublicLesson(pageBean);

        ResponseUtil.pageWrite(response,pageBean);

    }

    @RequestMapping("/details/listPublicLessonForS")
    public void listPublicLessonForS(  @RequestParam(value = "page", required = false) String page,  //当前多少页
                                   @RequestParam(value = "rows", required = false) String rows,  //有几行
                                   HttpServletResponse response   ) throws Exception {

        Page<PublicLesson> pageBean = new Page<PublicLesson>(Integer.parseInt(page), Integer.parseInt(rows));

        service.listPublicLessonForS(pageBean);

        ResponseUtil.pageWrite(response,pageBean);

    }


}
