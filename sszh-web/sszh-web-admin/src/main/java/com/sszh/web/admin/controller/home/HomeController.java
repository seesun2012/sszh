package com.sszh.web.admin.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页-主页
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "home/home";
    }
    
}