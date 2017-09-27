package www.lesson.pojo;

import java.util.List;

public class User {
    private String id;

    private String password;

    private String roleId;

    private String salt;

    private Boolean block = false;


    public String getSalt() {

        return id;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        block = block;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }




}
