package com.sszh.server.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 分布式-订单服务中心
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class SszhServerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerOrderApplication.class, args);
    }

}
