package com.sszh.server.sso.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置主控制器
 */
@RestController("system")
public class SystemController {

    @Value("${server.port}")
    private String port;

    /**
     * 获取端口配置
     */
    @RequestMapping(value = "port", method = RequestMethod.GET)
    @ResponseBody
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }


}
