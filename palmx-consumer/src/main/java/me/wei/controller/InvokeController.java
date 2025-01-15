package me.wei.controller;


import me.wei.service.InvokeClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InvokeController {

    @Resource
    private InvokeClient invokeClient;

    @RequestMapping("/empty")
    public String empty() {
        return "success";
    }

    @RequestMapping("/palmx")
    public String palmx() {
        return invokeClient.invokePalmx();
    }

    @RequestMapping("/palmx/multi")
    public String multiPalmx(int loopCount) {
        return invokeClient.loopInvokeDemo(loopCount);
    }

    @RequestMapping("/dubbo")
    public String dubbo() {
        return invokeClient.invokeDubbo();
    }
}
