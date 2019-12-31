package com.fanfan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "hello springboot";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String whoIm(){
        return "访问add成功";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String whohename(){
        return "访问search成功";
    }
}
