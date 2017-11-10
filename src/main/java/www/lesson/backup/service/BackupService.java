package www.lesson.backup.service;


import java.util.List;

public interface BackupService {

    //显示可以还原的备份数据
    List<String> listAllData();
    //备份当前备份数据
    void copyAllDataNow();
    //还原指定备份数据
    void restoreData(String time);
    //删除指定备份数据
    void deleteData(String time);
    //删除过期备份数据
    void deleteOldData();

}
