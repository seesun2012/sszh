package com.sszh.tx.lcn.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication              //Spring Boot 核心注解
@EnableTransactionManagerServer     //LCN分布式事务协调服务
public class SszhTxlcnTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhTxlcnTmApplication.class, args);
    }

}
