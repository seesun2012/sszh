package com.sszh.server.sso;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 分布式-用户服务中心
 */
@MapperScan("com.sszh.server.sso.mapper")       //mybatis的mapper接口映射路径
@SpringBootApplication                          //Spring Boot 核心注解
@EnableFeignClients                             //Feign 客户端
@EnableEurekaClient                             //Spring Cloud 客户端-服务发现
@EnableDistributedTransaction                   //LCN分布式事务-服务发现
public class SszhServerSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerSsoApplication.class, args);
    }

}
