package com.fanfan.pojo;

public class Role {
    private Integer roleId;

    private String roleName;

    private String roleCode;

    private Integer roleLevel;

    public Role(Integer roleId, String roleName, String roleCode, Integer roleLevel) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.roleLevel = roleLevel;
    }

    public Role() {
        super();
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
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }
}