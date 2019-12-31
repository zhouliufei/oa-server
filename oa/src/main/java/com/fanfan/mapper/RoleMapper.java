package com.fanfan.mapper;

import com.fanfan.dto.RoleMenuDTO;
import com.fanfan.dto.RoleRouteDTO;
import com.fanfan.pojo.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<RoleMenuDTO> queryAllRole();

    List<RoleRouteDTO> queryAllRoleUri();

    Integer queryTallestLevel(String userName);
}