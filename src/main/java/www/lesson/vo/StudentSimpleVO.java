package www.lesson.vo;

import www.lesson.pojo.Class;

public class StudentSimpleVO {
    String id;
    String name;
    String className;
    String year;

    public StudentSimpleVO(String id, String name, Class s){
        this.id=id;
        this.className=s.getName();
        this.year=s.getYear();
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
