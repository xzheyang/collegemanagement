package www.lesson.lesson.service;

import www.lesson.pojo.Lesson;

import java.io.File;

public interface LessonCUDService {

    //Excel添加课程
    void insertLessons(File file);

    //手动添加课程
    void insertLesson(Lesson lesson,String[] classIds);

    //修改课程
    void updateLesson(Lesson lesson);

    //删除课程  注:如果返回值是1代表有班级联系(可以强制一起删除),2代表有score不能删除
    int deleteLesson(String lessonId);

    //连通关系一起删除课程,有score不能删除
    void realDeleteLesson(String lessonId);

}
