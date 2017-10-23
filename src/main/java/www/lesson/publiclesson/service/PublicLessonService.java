package www.lesson.publiclesson.service;


import www.lesson.pojo.PublicLesson;

import java.io.File;

public interface PublicLessonService {

    //添加公选课(自动生成Id)
    void insert(PublicLesson publicLesson);
    void update(PublicLesson publicLesson);
    void delete(String Id);

    //批量添加公选课(自动生成Id,按照:课程名,老师名,老师Id,课程时间,学分,)
    void insert(File file);
    //修改公选课
    void updateWithId(PublicLesson publicLesson,String oldId);
    //添加公选课(有Id)
    void insertWithId(PublicLesson publicLesson);
}
