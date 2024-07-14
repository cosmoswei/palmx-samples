package me.wei.controller;


import me.wei.service.InvokeService;
import me.wei.util.ExcelUtil;
import me.wei.util.Metric;
import me.wei.util.MetricUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MetricController {

    private static final Logger log = LoggerFactory.getLogger(MetricController.class);
    @Resource
    private InvokeService invokeService;

    @GetMapping("/excel")
    public String downloadExcel(String param, HttpServletResponse response) {
        try {
            String fileName = "[" + param + "]数据指标" + System.currentTimeMillis() % 1000;
            ExcelUtil.writeExcelToResponse(response, MetricUtil.metricMap.get(param), Metric.class, fileName);
            log.info("Excel文件已生成,Size = `{}`", MetricUtil.metricMap.get(param));
            return "Excel文件已生成";
        } catch (IOException e) {
            e.printStackTrace();
            return "生成Excel失败";
        }
    }
}
