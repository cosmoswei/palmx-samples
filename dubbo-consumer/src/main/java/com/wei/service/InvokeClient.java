package com.wei.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class InvokeClient {

    @DubboReference
    private DubboServer dubboServer;

    public String invokeDubbo() {
        long start = System.currentTimeMillis();
        String res = dubboServer.dubboInvoke();
        long end = System.currentTimeMillis();
        return "method = invokeDubbo, res = " + res + ", duration = " + (end - start);
    }
}
