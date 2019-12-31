package com.fanfan.controller;

import com.fanfan.Util.JsonResult;
import com.fanfan.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/queryMenu",method = RequestMethod.GET)
    public JsonResult queryMenu(@RequestParam("roleCode") String roleCode) {
        return new JsonResult(menuService.queryMenu(roleCode));
    }

}
