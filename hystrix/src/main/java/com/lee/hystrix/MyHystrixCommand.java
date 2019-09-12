package com.lee.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MyHystrixCommand extends HystrixCommand<String> {
    private String name;

    public MyHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
//        try {
//            Thread.sleep(1000 * 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("get data");
        return this.name + ": " + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "服务回退";
    }

    // hystrix中的缓存技术
    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }
}
