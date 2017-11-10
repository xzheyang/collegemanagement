package www.lesson.backup.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackupDao {

    void copyAllData(@Param("time") String time);
    void restoreAllData(@Param("time") String time);
    void deleteData(@Param("time") String time);
    List<String> listAllData();

}
