package com.fanfan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fanfan.Util.RedisUtil;
import com.fanfan.pojo.Menu;
import com.fanfan.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Menu> queryMenu(String roleCode) {
        Set<String> result = redisUtil.queryAllFromSet(roleCode);
        Iterator<String> iterator = result.iterator();
        Map<String,Object> map=new HashMap<>();
        List<Menu> menus = new ArrayList<>();
        while (iterator.hasNext()) {
            String menuJson = iterator.next();
            JSONObject jsonObject = JSONObject.parseObject(menuJson);
            Menu menu = JSONObject.toJavaObject(jsonObject,Menu.class);
            menus.add(menu);
        }
        return menus;
    }
}
