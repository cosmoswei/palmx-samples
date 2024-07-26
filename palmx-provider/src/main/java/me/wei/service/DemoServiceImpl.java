package me.wei.service;

import me.xuqu.palmx.common.FlowControlType;
import me.xuqu.palmx.spring.PalmxService;

import java.util.concurrent.TimeUnit;

@PalmxService(flowControlLimitCount = 200000, flowControlLimitType = FlowControlType.SLIDING_WINDOW)
public class DemoServiceImpl implements DemoService {

    @Override
    public String demoSleepSecond(long l) {
        try {
            TimeUnit.MILLISECONDS.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "DemoServiceImpl success" + System.currentTimeMillis() % 10000;
    }
}
