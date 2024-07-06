package me.wei.controller;


import me.wei.service.InvokeService;
import me.wei.util.ExcelUtil;
import me.wei.util.Metric;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvokeController {

    @Resource
    private InvokeService invokeService;

    int empty = 0;
    int dubbo = 0;
    int palmx = 0;

    List<Metric> metrics = new ArrayList<>();

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
        metrics.add(metric);
        return res;
    }

    @RequestMapping("/metric")
    public String metric() {
        for (Metric metric : metrics) {
            System.out.print(metric.getTimes() + ",");
            System.out.println(metric.getInterval());
        }
        return "sonUtil.toJson(metrics)";
    }

    @GetMapping("/excel")
    public String downloadExcel(HttpServletResponse response) {
        try {
            String fileName = "数据指标" + System.currentTimeMillis() % 1000;
            ExcelUtil.writeExcelToResponse(response, metrics, Metric.class, fileName);
            return "Excel文件已生成";
        } catch (IOException e) {
            e.printStackTrace();
            return "生成Excel失败";
        }
    }
}
