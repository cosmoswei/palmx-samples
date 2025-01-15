package com.wei.controller;


import com.wei.service.InvokeClient;
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

    @RequestMapping("/dubbo")
    public String dubbo() {
        return invokeClient.invokeDubbo();
    }
}
