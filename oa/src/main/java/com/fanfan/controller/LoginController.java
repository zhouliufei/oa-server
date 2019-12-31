package com.fanfan.controller;

import com.fanfan.pojo.User;
import com.fanfan.pojo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody UserInfo userInfo) {
        return "hello word";
    }

    @RequestMapping(value = "/login-error",method = RequestMethod.GET)
    public String loginError(){
        return "/login-error";
    }
}
