package com.fanfan.dto;

import java.util.List;

public class RoleRouteDTO {

    private Integer roleId;

    private List<String> uriList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<String> getUriList() {
        return uriList;
    }

    public void setUriList(List<String> uriList) {
        this.uriList = uriList;
    }
}
