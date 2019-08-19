package com.sszh.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.sszh.*")                     //Spring Boot 核心注解
@EnableFeignClients(basePackages = "com.sszh.*")                            //Feign 客户端
@EnableEurekaClient                                                         //Spring Cloud 客户端-服务发现
public class SszhWebApiApplaction {

    public static void main(String[] args) {
        SpringApplication.run(SszhWebApiApplaction.class, args);
    }

}