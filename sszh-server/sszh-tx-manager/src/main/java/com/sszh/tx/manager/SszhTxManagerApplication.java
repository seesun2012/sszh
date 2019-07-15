package com.sszh.tx.manager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class SszhTxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhTxManagerApplication.class, args);
    }

}
