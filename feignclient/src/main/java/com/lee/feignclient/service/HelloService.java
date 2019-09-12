package com.lee.feignclient.service;

import com.lee.feignclient.feign_interface.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private EurekaClientFeign eurekaClientFeign;

    public String sayHello(){
        return eurekaClientFeign.sayHello();
    }

}
