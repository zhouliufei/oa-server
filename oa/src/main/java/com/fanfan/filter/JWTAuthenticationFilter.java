package com.fanfan.filter;

import com.fanfan.Util.RedisUtil;
import com.fanfan.Util.SpringContextUtil;
import com.fanfan.Util.StringUtil;
import com.fanfan.pojo.UserInfo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private RedisUtil redisUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        String token = request.getHeader("token");
        //判断是否有token
        if (token == null || !token.startsWith(StringUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = getAuthentication(token,request);
        } catch (Exception e) {
            response.sendError(401,"用户信息失效，请重新登录");
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        chain.doFilter(request, response);

    }

    /**
     * 解析token中的信息,并判断是否过期
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token,HttpServletRequest request) throws Exception {
        if(redisUtil == null) {
            redisUtil = SpringContextUtil.getBean(RedisUtil.class, request);
        }
        String username = redisUtil.get(token);
        if(username == null || username.trim().length() == 0) {
            throw new Exception("该账号已过期,请重新登陆");
        }
        redisUtil.expireTime(token);
        //存储权限
        UserInfo userInfo = new UserInfo(username,null,null,false,false,false,false);
        return new UsernamePasswordAuthenticationToken(userInfo, username, null);

    }

}
