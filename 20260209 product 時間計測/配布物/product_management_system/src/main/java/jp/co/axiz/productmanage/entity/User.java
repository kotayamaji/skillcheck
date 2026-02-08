package jp.co.axiz.productmanage.entity;

/**
 * usersテーブルのEntity
 */
public class User {
    private static final Integer ADMIN_ROLE_ID = 1; // 管理者を表すrole_idの値

    private Integer userId;
    private String userName;
    private String password;
    private String dispName;
    private Integer roleId;
    private String roleName;
    private Boolean isActive;

    public User() {
    }

    public User(Integer userId, String userName, String password, String dispName, Integer roleId, String roleName,
            Boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.dispName = dispName;
        this.roleId = roleId;
        this.roleName = roleName;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * role_idが管理者かを確認する
     * (管理者ならtrue)
     */
    public boolean isAdmin() {
        return roleId == ADMIN_ROLE_ID;
    }

}