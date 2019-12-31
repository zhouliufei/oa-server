package com.fanfan.auth;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //这里可以根据实际情况，来确定是跳转到页面或者json格式。
        //如果是返回json格式，那么我们这么写
        logger.info("登录成功");
        Map<String,String> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");
        map.put("authentication", JSONObject.toJSONString(authentication));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));

    }

}
