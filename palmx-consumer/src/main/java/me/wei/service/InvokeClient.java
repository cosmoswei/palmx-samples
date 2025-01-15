package me.wei.service;

import me.xuqu.palmx.spring.PalmxClient;
import org.springframework.stereotype.Component;

@Component
public class InvokeClient {

    @PalmxClient
    private PalmxService palmxService;

    public String invokePalmx() {
        long start = System.currentTimeMillis();
        String res = palmxService.demoInvoke();
        long end = System.currentTimeMillis();
        return "method = invokePalmx, res = " + res + ", duration = " + (end - start);
    }

    public String loopInvokeDemo(int loopCount) {
        long start = System.currentTimeMillis();
        String res = null;
        for (int i = 0; i < loopCount; i++) {
            res = palmxService.demoInvoke();
        }
        long end = System.currentTimeMillis();
        return "method = invokeDemo, res = " + res + ", duration = " + (end - start);
    }
}
