package me.wei.benchmark;

import me.xuqu.palmx.spring.PalmxClient;
import org.springframework.stereotype.Component;

@Component
public class GreetingClient {
    
    @PalmxClient
    private GreetingService greetingService;

    public String callSayHello(String name) {
        return greetingService.sayHello(name);
    }
}
