package com.sszh.server.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 分布式-用户服务中心
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class SszhServerSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerSsoApplication.class, args);
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//        c.setIgnoreUnresolvablePlaceholders(true);
//        return c;
//    }

}
