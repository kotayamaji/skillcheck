package jp.co.axiz.axizmarket.entity;

/**
 * usersテーブルのEntity
 */
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String dispName;
    private Boolean isActive;

    public User() {
    }

    public User(Integer userId, String userName, String password, String dispName, Boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.dispName = dispName;
        this.isActive = isActive;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDispName() {
        return dispName;
    }

    public void setDispName(String dispName) {
        this.dispName = dispName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}