package me.wei.service;

import me.xuqu.palmx.spring.PalmxClient;
import org.springframework.stereotype.Component;

@Component
public class PalmxService {

    @PalmxClient
    private DemoService demoService;
    @PalmxClient
    private TestService testService;

    public String invokeDemo() {
        return "invokeDemo = " + demoService.demoSleepSecond(1L);
    }

    public String invokeTest() {
        return "invokeTest = " + testService.testSleepSecond(1L);
    }
}
