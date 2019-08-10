package com.sszh.server.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 分布式-订单服务中心
 */
@MapperScan(basePackages = {"com.sszh.server.order.mapper"})                //mybatis的mapper接口映射路径
@SpringBootApplication(scanBasePackages = "com.sszh.server.order.*")        //Spring Boot 核心注解
@EnableFeignClients(basePackages = "com.sszh.server.*")                     //Feign 客户端
@EnableEurekaClient                                                         //Spring Cloud 客户端-服务发现
@EnableTransactionManagement                                                //Spring事务
@EnableDiscoveryClient
@EnableDistributedTransaction                                               //LCN分布式事务-服务发现
public class SszhServerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerOrderApplication.class, args);
    }

}
