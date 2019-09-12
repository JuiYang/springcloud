package com.lee.feignclient.hystrix;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HouseRemoteClientFallbackFactory implements FallbackFactory<HouseRemoteClient> {
    private Logger logger = LoggerFactory.getLogger(HouseRemoteClientFallbackFactory.class);

    @Override
    public HouseRemoteClient create(Throwable throwable) {
        logger.error("HouseRemoteClient fall back case: ", throwable);
        return new HouseRemoteClient() {
            @Override
            public String hello() {
                return "HouseRemoteClientFallbackFactory: fail back";
            }
        };
    }
}
