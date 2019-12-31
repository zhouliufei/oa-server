package com.fanfan.filter;

import com.alibaba.fastjson.JSONObject;
import com.fanfan.Util.RedisUtil;
import com.fanfan.Util.SpringContextUtil;
import com.fanfan.Util.StringUtil;
import com.fanfan.pojo.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private RedisUtil redisUtil;

    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    /**
     * 接收并解析用户登陆信息  /login,
     *为已验证的用户返回一个已填充的身份验证令牌，表示成功的身份验证
     *返回null，表明身份验证过程仍在进行中。在返回之前，实现应该执行完成该过程所需的任何额外工作。
     *如果身份验证过程失败，就抛出一个AuthenticationException
     *
     *
     * @param request  从中提取参数并执行身份验证
     * @param response 如果实现必须作为多级身份验证过程的一部分(比如OpenID)进行重定向，则可能需要响应
     * @return 身份验证的用户令牌，如果身份验证不完整，则为null。
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //得到用户登陆信息,并封装到 Authentication 中,供自定义用户组件使用.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        username = username.trim();
        ArrayList<GrantedAuthorityImpl> authorities = new ArrayList<>();
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password, authorities);

        //authenticate()接受一个token参数,返回一个完全经过身份验证的对象，包括证书.
        // 这里并没有对用户名密码进行验证,而是使用 AuthenticationProvider
        // 提供的 authenticate 方法返回一个完全经过身份验证的对象，包括证书.
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

//UsernamePasswordAuthenticationToken 是 Authentication 的实现类
        setDetails(request, authenticationToken);
        Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
        return authenticate;
    }


    /**
     * 登陆成功后,此方法会被调用,因此我们可以在次方法中生成token,并返回给客户端
     * 并返回用户的菜单列表
     * @param request
     * @param response
     * @param chain
     * @param authResult
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        if(redisUtil == null) {
            redisUtil = SpringContextUtil.getBean(RedisUtil.class, request);
        }
        if(objectMapper == null) {
            objectMapper = SpringContextUtil.getBean(ObjectMapper.class,request);
        }
        String token = UUID.randomUUID().toString();
        redisUtil.set(token,null,authResult.getName());
        response.addHeader("token", StringUtil.TOKEN_PREFIX + token);
        logger.info("token生成成功");

        UserInfo userInfo = (UserInfo) authResult.getPrincipal();
        String roleCode = userInfo.getRole();
        Map<String,Object> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");
        map.put("role",roleCode);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
