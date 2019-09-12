package com.lee.feignclient.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    //RestTemplate中添加LoadBalancerClient实现客户端负载均衡
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        // RestTemplate是spring提供的用于访问Rest服务的客户端，提供了多种便捷访问远程http服务的方法
        return new RestTemplate();
    }
}
