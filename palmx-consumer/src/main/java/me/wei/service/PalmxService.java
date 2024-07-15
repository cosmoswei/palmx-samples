package me.wei.service;

import me.xuqu.palmx.spring.PalmxClient;
import org.springframework.stereotype.Component;

@Component
public class PalmxService {

    @PalmxClient
    private DemoService demoService;

    public String invoke() {
        return "invokePalmx = " + demoService.demoSleepSecond(1L);
    }
}
