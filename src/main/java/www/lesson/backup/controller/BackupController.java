package www.lesson.backup.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import www.lesson.backup.service.BackupService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BackupController {

    @Resource(name = "backupServiceImpl")
    BackupService service;

    @RequiresRoles("admin")
    @RequestMapping("/admin/listBackupData")
    @ResponseBody
    public Map listBackupData(){

        Map result = new HashMap();
        List Data =service.listAllData();
        for(int i=0;i<Data.size();i++){
            Map map = new HashMap();
            map.put("id",Data.get(i));
            Data.set(i,map);
        }


        result.put("total",Data.size());
        result.put("rows",Data);


        return result;
    }


    @RequiresRoles("admin")
    @RequestMapping("/admin/deleteBackupData")
    @ResponseBody
    public Map deleteBackupData(String times){
        boolean result = true;
        Map map = new HashMap();

        String[] time = times.split(",");

        try {
            for(String id:time){
                service.deleteData(id);
            }
        }catch (Exception e){
            result = false;
        }

        map.put("result",result);

        return map;
    }


    @RequiresRoles("admin")
    @RequestMapping("/admin/copyBackupData")
    @ResponseBody
    public Map copyBackupData(){
        boolean result = true;
        Map map = new HashMap();

        try {
        service.copyAllDataNow();
        }catch (Exception e){
            result = false;
        }
        map.put("result",result);

        return map;
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin/restoreBackupData")
    @ResponseBody
    public Map restoreBackupData(String time){
        boolean result = true;
        Map map = new HashMap();

        try {
            service.restoreData(time);
        }catch (Exception e){
            result = false;
        }
        map.put("result",result);

        return map;
    }


    @RequestMapping("/timeCopyBackupData")
    public void timeCopyBackupData(){

    }


}
