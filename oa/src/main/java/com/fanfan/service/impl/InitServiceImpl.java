package com.fanfan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fanfan.Util.RedisUtil;
import com.fanfan.Util.StringUtil;
import com.fanfan.dto.RoleMenuDTO;
import com.fanfan.dto.RoleRouteDTO;
import com.fanfan.mapper.RoleMapper;
import com.fanfan.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void init() {
        //存储每个角色的菜单到redis
        storeMenu();
        //存储每个角色能访问的url到redis
        storeRequestUrl();
    }

    private void storeRequestUrl() {
        List<RoleRouteDTO> list = roleMapper.queryAllRoleUri();
        for(int i = 0; i < list.size();i++) {
            RoleRouteDTO roleRouteDTO = list.get(i);
            redisUtil.delete(StringUtil.URI_PREFIX + roleRouteDTO.getRoleId());
            for(int j = 0; j < roleRouteDTO.getUriList().size();j++) {
                redisUtil.addToSet(StringUtil.URI_PREFIX + roleRouteDTO.getRoleId(),roleRouteDTO.getUriList().get(j));
            }
        }
    }


    /**
     * 拿到每个角色对应的菜单列表
     */
    private void storeMenu() {
        List<RoleMenuDTO> list = roleMapper.queryAllRole();
        for(int i = 0; i < list.size();i++) {
            RoleMenuDTO roleMenuDTO = list.get(i);
            redisUtil.delete(roleMenuDTO.getRoleCode());
            for(int j = 0; j < roleMenuDTO.getMenuList().size();j++) {
                String value = JSONObject.toJSONString(roleMenuDTO.getMenuList().get(j));
                redisUtil.addToSet(roleMenuDTO.getRoleCode(),value);
            }
        }
    }
}
