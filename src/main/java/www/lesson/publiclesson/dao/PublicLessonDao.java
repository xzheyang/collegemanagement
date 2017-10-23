package www.lesson.publiclesson.dao;


import www.lesson.pojo.PublicLesson;

import java.util.List;

public interface PublicLessonDao {

    //添加公选课
    void insertPublicLesson(PublicLesson publicLesson);
    //添加自动生成Id的公选课
    void insertPublicLessonNoId(PublicLesson publicLesson);
    //删除公选课
    void deletePublicLesson(String Id);
    //修改公选课
    void updatePublicLesson(PublicLesson publicLesson);
    //返回单个公选课
    PublicLesson getPublicLesson(String Id);

    //公选课总数
    long countPublicLesson();
    //分页返回公选课
    List<PublicLesson> listPublicLesson(int start,int end);

    //是否存在公选课
    boolean existPublicLesson(String Id);

}
