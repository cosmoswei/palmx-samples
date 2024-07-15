package me.wei.benchmark;

import me.xuqu.palmx.spring.PalmxService;

// 服务接口的实现类
@PalmxService
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
