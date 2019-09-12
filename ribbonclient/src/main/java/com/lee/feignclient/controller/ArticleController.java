package com.lee.feignclient.controller;

import com.lee.feignclient.hystrix.HouseRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ribbon")
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private HouseRemoteClient houseRemoteClient;

    // 传统的远程服务访问方式
    @GetMapping("/hello")
    public String callHello() {
        return restTemplate.getForObject("http://localhost:8888/user/hello", String.class);
    }

    // 采用Eureka(RestTemplate)服务名称的访问方式
    @GetMapping("/hello1")
    public String callHello1() {
        return restTemplate.getForObject("http://eureka-client-user-service/user/hello", String.class);
    }

    // 采用feign调用接口
    @GetMapping("/hello2")
    public String callHello2() {
        String result = houseRemoteClient.hello();
        System.out.println("调用结果：" + result);
        return result;
    }

    @GetMapping("/choose")
    public Object chooseUrl(){
        ServiceInstance instance = loadBalancerClient.choose("eureka-client-user-service");
        return instance;
    }

}
