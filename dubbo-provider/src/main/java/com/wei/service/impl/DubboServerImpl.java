package com.wei.service.impl;

import com.wei.service.DubboServer;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.TimeUnit;

@DubboService
public class DubboServerImpl implements DubboServer {

    @Override
    public String sleepSecond(long l) {
        try {
            TimeUnit.MILLISECONDS.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "demoSleepSecond success and " + System.currentTimeMillis() % 10000;
    }

    private int cnt = 0;

    @Override
    public String dubboInvoke() {
        return (cnt++) + " demoInvoke success " + System.currentTimeMillis() % 10000;
    }
}
