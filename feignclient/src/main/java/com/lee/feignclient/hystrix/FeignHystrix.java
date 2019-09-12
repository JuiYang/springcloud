package com.lee.feignclient.hystrix;

import com.lee.feignclient.feign_interface.EurekaClientFeign;
import org.springframework.stereotype.Component;

@Component
public class FeignHystrix implements EurekaClientFeign {

    @Override
    public String sayHello() {
        return "FeignHystrix: fall back";
    }
}
