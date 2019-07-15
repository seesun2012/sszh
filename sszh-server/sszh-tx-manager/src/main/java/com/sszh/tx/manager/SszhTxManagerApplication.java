package com.sszh.tx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SszhTxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhTxManagerApplication.class, args);
    }

}
