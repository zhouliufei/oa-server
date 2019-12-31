package com.fanfan.controller;

import com.fanfan.pojo.User;
import com.fanfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public void regist(@RequestBody User user) {
         userService.register(user);
    }

}
