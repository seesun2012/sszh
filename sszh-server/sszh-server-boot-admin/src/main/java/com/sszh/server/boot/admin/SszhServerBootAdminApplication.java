package com.sszh.server.boot.admin;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SszhServerBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SszhServerBootAdminApplication.class, args);
    }

}
