package jp.co.axiz.web.from;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;

public class DeleteForm {
    
    private Integer userId;

    @NotBlank(groups = { Default.class, ValidationGroupUpdate.class })
    private String loginId;

    @NotBlank
    private String userName;

    @NotBlank
    private String tel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer roleId;

    private String roleName;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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


}
