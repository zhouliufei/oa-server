package com.fanfan.pojo;

public class UserRole {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Integer roleLevel;

    public UserRole(Integer id, Integer userId, Integer roleId, Integer roleLevel) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.roleLevel = roleLevel;
    }

    public UserRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }
}