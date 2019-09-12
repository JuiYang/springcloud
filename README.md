# RestTemplate
spring提供用于访问Test服务的客户端，TestTemplate提供了多种访问远程http服务的方法。
通过配置TestTemplate来调用接口。
```
@Configuration
public class BeanConfiguration {
    @Bean
    public RestTemplate getRestTemplate() {
        // RestTemplate是spring提供的用于访问Rest服务的客户端，提供了多种便捷访问远程http服务的方法
        return new RestTemplate();
    }
}
```

# @LoadBalanced 注解
Q1: RestTemplate上加了@LoadBalanced之后，RestTemplate就与Eureka结合，可以使用服务名去调用接口还可以达到负载均衡的作用？

A: LoadBalanced原理：主要逻辑是给RestTemplate增加拦截器，在请求之前对请求地址进行替换，或者根据具体的负载策略选择服务器的地址，
    然后再去调用。
    
Q2: Eureka的重试机制

A2: Eureka集群是基于AP原则，牺牲了数据一致性，但是每个Eureka服务都会保存注册的服务信息，当注册的客户端与Eureka的心跳无法
    保持时(网络/服务宕机)，Eureka中还会保留注册信息一段时间。这个时候客户端可能会获取到已经挂掉的服务信息，这样导致服务
    请求失败。
    重试机制就是为了解决当Ribbon发生请求的服务不可到达时，重新请求另外的服务。

Q2: LoadBalanced的负载均衡策略有哪些？

A2: Ribbon客户端负载均衡器；Dubbo服务调用负载均衡器；

# Hystrix断路器的回退功能
- 实现1：通过fallback实现回退功能
  @FeignClient(fallback = HouseRemoteClientFallBack.class)
- 实现2：通过FallbackFactory实现回退功能，并且能输出触发回退的原因
  @FeignClient(fallbackFactory = HouseRemoteClientFallbackFactory.class)

# Hystrix
### 介绍一下Hystrix:
- 在分布式系统中，服务与服务之间的依赖错综复杂，某些服务出现故障时会导致依赖于他们的其他服务会出现远程调度的线程阻塞。
Hystrix提供了熔断功能，能够阻止分布式系统中出现联动故障。Hystrix通过隔离服务的访问点阻止联动故障，并为故障提供解决方案，从而提高了整个分布式系统的弹性。
### 雪崩效应
- 在分布式系统中，某个服务的单个点的请求故障会导致用户请求处于阻塞状态，最终结果就是整个服务的线程资源耗尽。由于服务的依赖性，会导致依赖于该故障服务的其他服务也处于线程阻塞状态，最终导致这些服务线程资源消耗殆尽，直到不可用，从而导致整个服务访问系统不可用。
### Hystrix设计原理
- 防止单个服务的故障耗尽整个服务的Serlet容器的线程资源。
- 快速失败机制，如果某个服务出现故障，则调用该服务的请求快速失败，而不是线程等待。
- 提供回退容错机制，在请求发生故障时，提供设定好的回退方案。
- 使用熔断机制，防止故障扩散到其他服务。
- 提供熔断监控组件,可以实时监控熔断器的状态。
### Hystrix的工作机制
- 工作机制如图。首先，当服务的某个API接口的失败次数在一定时间内小于设定的阈值时，熔断器处于关闭状态，该API继续正常的提供服务。当该API接口处理请求失败的次数大于设定阈值时，Hystrix会判定该API接口出现故障，打开熔断器，这时请求该API接口会执行快速失败的逻辑，不执行业务逻辑，请求的线程不会处于阻塞状态。
处于打开状态的熔断器，一段时间后会处于半打开状态，并将一定数量的请求执行到正常逻辑。剩余的请求会执行快速失败，若执行正常逻辑的请求失败了，则熔断器继续打开；若成功了，则将熔断器关闭。这样熔断器就具有了自我修复的能力。

### turbine:
@EnableTurbine注解未生效