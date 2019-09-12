package com.tcl.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeeApplication.class, args);
    }

}
