package com.fanfan.Util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

public class SpringContextUtil {

    public static <T> T getBean(Class<T> clazz, HttpServletRequest request){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }

}
