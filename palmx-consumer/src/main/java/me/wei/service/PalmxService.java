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
        long start = System.currentTimeMillis();
        String res = demoService.demoInvoke();
        long end = System.currentTimeMillis();
        return "method = invokeDemo, res = " + res + ", duration = " + (end - start);
    }


    public String loopInvokeDemo(int loopCount) {
        long start = System.currentTimeMillis();
        String res = null;
        for (int i = 0; i < loopCount; i++) {
            res = demoService.demoInvoke();
        }
        long end = System.currentTimeMillis();
        return "method = invokeDemo, res = " + res + ", duration = " + (end - start);
    }

    public String invokeTest() {
        return "invokeTest = " + testService.testSleepSecond(1L);
    }
}
