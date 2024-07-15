package me.wei.service;

import me.xuqu.palmx.spring.PalmxService;

import java.util.concurrent.TimeUnit;

@PalmxService
public class InvokeServiceImpl implements InvokeService {

    @Override
    public String invokePalmx(long l) {
        try {
            TimeUnit.MILLISECONDS.sleep(l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success" + System.currentTimeMillis() % 10000;
    }
}
