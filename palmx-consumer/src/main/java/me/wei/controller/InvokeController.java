package me.wei.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.wei.service.InvokeService;

import javax.annotation.Resource;

@RestController
public class InvokeController {

    @Resource
    private InvokeService invokeService;

    int empty = 0;
    int dubbo = 0;
    int palmx = 0;

    @RequestMapping("/empty")
    public String empty() {
        return "empty = " + empty++;
    }

    @RequestMapping("/palmx")
    public String palmx() {
        return invokeService.invokePalmx() + palmx++;
    }
}
