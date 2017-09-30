package www.lesson.pojo;

import java.util.List;

public class Teacher {

    private String id;

    private String userName;

    private Boolean woman;

    private String birthday;

    private String identification;

    private List<String> classIds;

    private List<String> lessonId;


    //判断性别(String转boolean)
    public void setWoman(String s){
        if(s.equals("true")||s.equals("1")){
            this.woman=true;
        }else if(s.equals("false")||s.equals("0")){
            this.woman=false;
        }else{
            this.woman=null;
        }
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

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
