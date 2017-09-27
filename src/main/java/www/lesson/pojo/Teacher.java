package www.lesson.pojo;

import java.util.List;

public class Teacher {

    private String id;

    private String userName;

    private Boolean woman;

    private String birthday;

    private List<String> classIds;

    private List<String> lessonId;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getWoman() {
        return woman;
    }

    public void setWoman(Boolean is) {
        woman = is;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }

    public List<String> getLessonId() {
        return lessonId;
    }

    public void setLessonId(List<String> lessonId) {
        this.lessonId = lessonId;
    }
}
