package com.lee.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixCacheMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        // 同步调用
        String result = new MyHystrixCommand("leeyu").execute();
        System.out.println(result);

        // 异步调用
        Future<String> future = new MyHystrixCommand("leeyu").queue();
        System.out.println(future.get());
        context.shutdown();

        // 以上输出结果： get data
        // leeyu: hystrix-MyGroup-1
        // leeyu: hystrix-MyGroup-1
    }
}
