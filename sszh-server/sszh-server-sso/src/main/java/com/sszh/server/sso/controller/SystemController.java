package com.sszh.server.sso.controller;

import com.sszh.server.sso.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置主控制器
 */
@RestController
public class SystemController {

    @Autowired
    private SystemConfig systemConfig;

    /**
     * 获取端口配置
     */
    @RequestMapping(value = "/port", method = RequestMethod.GET)
    @ResponseBody
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + systemConfig.port;
    }

}
