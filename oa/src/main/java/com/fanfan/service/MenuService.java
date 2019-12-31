package com.fanfan.service;

import com.fanfan.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> queryMenu(String roleCode);
}
