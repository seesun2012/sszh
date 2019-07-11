package com.sszh.server.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 分布式-订单服务中心
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, RedisAutoConfiguration.class })
@EnableDiscoveryClient
@EnableFeignClients
public class SszhServerOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerOrderApplication.class, args);
    }

}
