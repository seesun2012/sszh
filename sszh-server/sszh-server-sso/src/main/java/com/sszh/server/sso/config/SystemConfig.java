package com.sszh.server.sso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统参数
 */
@Component
public class SystemConfig {

    @Value("${system.port}")
    public Integer port;
    @Value("${system.name}")
    public  String name;

}
