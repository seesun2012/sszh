package com.sszh.server.boot.admin;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 健康监控服务端
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient                                                         //Spring Cloud 客户端-服务发现
public class SszhServerBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerBootAdminApplication.class, args);
    }

}