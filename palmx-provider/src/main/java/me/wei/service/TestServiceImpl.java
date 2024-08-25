package me.wei.service;

import me.xuqu.palmx.common.FlowControlType;
import me.xuqu.palmx.spring.PalmxService;

import java.util.concurrent.TimeUnit;

@PalmxService(flowControlLimitCount = 100000, flowControlLimitType = FlowControlType.ADAPTIVE)
public class TestServiceImpl implements TestService {

    @Override
    public String testSleepSecond(long l) {
        try {
            System.gc();
            TimeUnit.MILLISECONDS.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "TestServiceImpl success" + System.currentTimeMillis() % 10000;
    }
}
