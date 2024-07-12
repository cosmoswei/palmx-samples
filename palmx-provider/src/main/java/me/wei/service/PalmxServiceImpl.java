package me.wei.service;

@me.xuqu.palmx.spring.PalmxService
public class PalmxServiceImpl implements PalmxService {

    @Override
    public String invokePalmx(long l) {
        return "success" + System.currentTimeMillis() % 10000;
    }
}
