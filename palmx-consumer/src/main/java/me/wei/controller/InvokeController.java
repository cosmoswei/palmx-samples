package me.wei.controller;


import me.wei.service.InvokeService;
import me.wei.util.ExcelUtil;
import me.wei.util.Metric;
import me.wei.util.MetricUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InvokeController {

    @Resource
    private InvokeService invokeService;

    int empty = 0;
    int palmx = 0;

    @RequestMapping("/empty")
    public String empty() {
        long start = System.currentTimeMillis();
        empty++;
        long end = System.currentTimeMillis();
        long res = end - start;
        return "success  + " + res;

    }

    @RequestMapping("/palmx")
    public String palmx() {
        long start = System.currentTimeMillis();
        String res = invokeService.invokePalmx();
        long end = System.currentTimeMillis();
        Metric metric = new Metric(palmx++, end - start);
        MetricUtil.metricMap.getOrDefault("palmx", new ArrayList<>()).add(metric);
        return res;
    }
}
