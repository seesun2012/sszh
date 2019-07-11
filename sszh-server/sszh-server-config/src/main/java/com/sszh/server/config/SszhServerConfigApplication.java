package com.sszh.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 分布式-配置服务中心
 */
@SpringBootApplication
@SpringBootConfiguration
@EnableConfigServer
@EnableDiscoveryClient
public class SszhServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerConfigApplication.class, args);
    }

}