package com.lee.feignclient.feign_interface;

import com.lee.feignclient.config.FeignConfig;
import com.lee.feignclient.hystrix.FeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client-user-service", configuration = FeignConfig.class,
        fallback = FeignHystrix.class)
public interface EurekaClientFeign {

    @GetMapping(value = "/user/hello")
    String sayHello();
}
