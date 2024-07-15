package me.wei.service;

import java.util.concurrent.TimeUnit;

@me.xuqu.palmx.spring.PalmxService
public class PalmxServiceImpl implements PalmxService {

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
