package com.sszh.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 分布式-网关
 */
@SpringBootApplication
//加上网关注解
@EnableZuulProxy
public class SszhZullGetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SszhZullGetwayApplication.class, args);
    }
}