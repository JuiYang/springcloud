package com.lee.hystrix;

import java.util.concurrent.ExecutionException;

public class ClearHystrixCacheMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        HystrixRequestContext context = HystrixRequestContext.initializeContext();

//        String result = new ClearCacheHystrixCommand("leeyu").execute();
//        System.out.println(result);

        // 清除缓存
        ClearCacheHystrixCommand.flushCache("leeyu");

//        Future<String> future = new ClearCacheHystrixCommand("leeyu").queue();
//        System.out.println(future.get());
    }
}
