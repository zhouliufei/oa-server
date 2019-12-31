package com.fanfan.service.impl;

import com.fanfan.Util.RedisUtil;
import com.fanfan.Util.StringUtil;
import com.fanfan.mapper.RoleMapper;
import com.fanfan.mapper.UserMapper;
import com.fanfan.service.RbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Service("rbacService")
public class RbacServiceImpl implements RbacService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            String userName = ((UserDetails) principal).getUsername();
            //根据用户名读取用户的所有权限
            Integer roleId = roleMapper.queryTallestLevel(userName);
            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            if(redisUtil.sismember(StringUtil.URI_PREFIX + roleId,request.getRequestURI())) {
                hasPermission = true;
            }
        }
        return hasPermission;
    }
}
