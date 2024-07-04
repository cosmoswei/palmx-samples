package me.wei.service;

import me.xuqu.palmx.spring.PalmxClient;
import org.springframework.stereotype.Component;

@Component
public class InvokeService {

    @PalmxClient
    private PalmxService palmxService;

    public String invokePalmx() {
        return "invokePalmx = " + palmxService.invokePalmx(1L);
    }
}
