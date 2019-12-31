package com.fanfan.mapper;

import com.fanfan.pojo.MenuRoute;

public interface MenuRouteMapper {
    int deleteByPrimaryKey(Integer routeId);

    int insert(MenuRoute record);

    int insertSelective(MenuRoute record);

    MenuRoute selectByPrimaryKey(Integer routeId);

    int updateByPrimaryKeySelective(MenuRoute record);

    int updateByPrimaryKey(MenuRoute record);
}