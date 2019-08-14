package com.sszh.tx.lcn.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication                                                      //Spring Boot 核心注解
@EnableTransactionManagerServer                                             //LCN分布式事务协调服务
@EnableEurekaClient                                                         //Spring Cloud 客户端-服务发现
public class SszhTxlcnTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhTxlcnTmApplication.class, args);
    }

}
