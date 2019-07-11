package com.sszh.server.sso.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置主控制器
 */
@RestController
public class SystemController {

    @Value("${server.port}")
    private String port;

    @Value("${unionpay.redis.hosts}")
    private String hosts;


    /**
     * 获取端口配置
     */
    @RequestMapping(value = "/port", method = RequestMethod.GET)
    @ResponseBody
    public String home(@RequestParam String name) {
        String str = hosts;
        return "hi " + name + ",i am from port:" + port + ":" + str;
    }

}
