package me.wei.controller;


import me.wei.service.PalmxService;
import me.wei.util.Metric;
import me.wei.util.MetricUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvokeController {

    @Resource
    private PalmxService palmxService;

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
        String res = palmxService.invokeDemo();
        long end = System.currentTimeMillis();
        Metric metric = new Metric(palmx++, end - start);
        List<Metric> metricList = MetricUtil.metricMap.getOrDefault("palmx", new ArrayList<>());
        metricList.add(metric);
        MetricUtil.metricMap.put("demo", metricList);
        return res;
    }

    @RequestMapping("/test")
    public String test() {
        long start = System.currentTimeMillis();
        String res = palmxService.invokeTest();
        long end = System.currentTimeMillis();
        Metric metric = new Metric(palmx++, end - start);
        List<Metric> metricList = MetricUtil.metricMap.getOrDefault("palmx", new ArrayList<>());
        metricList.add(metric);
        MetricUtil.metricMap.put("test", metricList);
        return res;
    }
}
