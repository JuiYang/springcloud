package com.tcl.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


//启动服务注册中心
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer02 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer02.class, args);
    }
}
