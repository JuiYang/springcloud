package com.lee.feignclient.hystrix;

import com.lee.feignclient.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// feign调用接口， value：服务名称，path：请求路径, configuration：配置feign日志级别
@FeignClient(value = "eureka-client-user-service", path = "/user", configuration = FeignConfiguration.class,
//        fallback = HouseRemoteClientFallBack.class
        fallbackFactory = HouseRemoteClientFallbackFactory.class
)
public interface HouseRemoteClient {

    @GetMapping("/hello")
    String hello();
}
