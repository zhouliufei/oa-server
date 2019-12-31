package com.fanfan.auth;

import com.fanfan.pojo.UserInfo;
import com.fanfan.service.UserInfoService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {

    private UserInfoService userInfoService;

    public MyAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userInfoService = (UserInfoService) userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        UserInfo userInfo = (UserInfo) userInfoService.loadUserByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if(!userInfo.getPassword().equals(password)) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        // 构建返回的用户登录成功的token
        return  new UsernamePasswordAuthenticationToken(userInfo, password, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return  (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
