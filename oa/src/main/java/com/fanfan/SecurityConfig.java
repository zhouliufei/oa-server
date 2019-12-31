package com.fanfan;


import com.fanfan.auth.MyAuthenticationFailHander;
import com.fanfan.auth.MyAuthenticationProvider;
import com.fanfan.auth.MyAuthenticationSuccessHandler;
import com.fanfan.filter.JWTAuthenticationFilter;
import com.fanfan.filter.JWTLoginFilter;
import com.fanfan.service.UserInfoService;
import com.sun.org.apache.xpath.internal.patterns.NodeTestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用 csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //允许以下请求
                .antMatchers("/regist").permitAll()
                .antMatchers("/test").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                //验证登陆
                .addFilter(jwtLoginFilter())
                //验证token
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义provider,验证用户名和密码
        auth.authenticationProvider(new MyAuthenticationProvider(userDetailsService));
    }


    /**
     * 自定义登陆验证接口
     */
    public JWTLoginFilter jwtLoginFilter() throws Exception {
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationManager());
        //只有post请求才拦截
        jwtLoginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        jwtLoginFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        jwtLoginFilter.setAuthenticationFailureHandler(myAuthenticationFailHander);
        return jwtLoginFilter;
    }
}
