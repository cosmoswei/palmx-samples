package me.wei.service.impl;

import me.wei.service.PalmxService;
import me.xuqu.palmx.common.FlowControlType;

import java.util.concurrent.TimeUnit;

@me.xuqu.palmx.spring.PalmxService(flowControlLimitCount = 200000, flowControlLimitType = FlowControlType.SLIDING_WINDOW)
public class PalmxServiceImpl implements PalmxService {

    @Override
    public String demoSleepSecond(long l) {
        try {
            TimeUnit.MILLISECONDS.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "demoSleepSecond success and " + System.currentTimeMillis() % 10000;
    }

    private int cnt = 0;

    @Override
    public String demoInvoke() {
        return (cnt++) + " demoInvoke success " + System.currentTimeMillis() % 10000;
    }
}
