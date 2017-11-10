package www.lesson.backup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.lesson.backup.dao.BackupDao;
import www.lesson.backup.service.BackupService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BackupServiceImpl implements BackupService {

    @Resource
    BackupDao backupDao;

    public void copyAllDataNow() {
        //获得当前时间的指定格式
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateFormat.format(date);

        backupDao.copyAllData(now);
    }

    public void restoreData(String time) {
        backupDao.restoreAllData(time);
    }

    public List<String> listAllData() {
        List<String> data = backupDao.listAllData();
        //获得时间
        for(int i=0;i<data.size();i++){
            String key = data.get(i);
            data.set(i,key.substring(key.indexOf("_")+1));
        }

        return data;
    }

    public void deleteData(String time) {
        backupDao.deleteData(time);
    }

    public void deleteOldData() {

    }


}
