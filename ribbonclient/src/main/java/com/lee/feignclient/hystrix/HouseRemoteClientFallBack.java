package com.lee.feignclient.hystrix;

import org.springframework.stereotype.Component;

@Component
public class HouseRemoteClientFallBack implements HouseRemoteClient {

    @Override
    public String hello() {
        return "HouseRemoteClientFallBack: fail back";
    }
}
