package com.sszh.server.boot.admin;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 健康监控服务端
 */
@SpringBootApplication
@EnableAdminServer
public class SszhServerBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerBootAdminApplication.class, args);
    }

}
