package com.fanfan.dto;

import com.fanfan.pojo.Menu;
import com.fanfan.pojo.Role;

import java.util.List;

public class RoleMenuDTO extends Role {

    private List<Menu> menuList;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
