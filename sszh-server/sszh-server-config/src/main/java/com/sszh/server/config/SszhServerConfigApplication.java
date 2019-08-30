package com.sszh.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 分布式-配置服务中心
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class SszhServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerConfigApplication.class, args);
    }

}