package com.cjgod.candy.dao.db.dto;

import java.math.BigDecimal;

/**
 * Created by lichunjiang on 2016/12/19.
 */
public class SystemAdministrator {
    private Integer id;
    private BigDecimal serialNumber;  //编号
    private String username; //用户名
    private String password;//密码
    private String tel; //电话
    private String email; //邮箱
    private String status; //状态
    private Integer roleId;//角色id
    private String roleName;//角色名称
    private String realName;//真实姓名

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(BigDecimal serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SystemAdministrator{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}

