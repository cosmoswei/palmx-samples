package me.wei.util;

import com.alibaba.excel.EasyExcel;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil {

    public static <T> void writeExcelToResponse(HttpServletResponse response, List<T> dataList, Class<T> dataClass) throws IOException {
        // 设置响应头和内容类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 设置下载的文件名
        String fileName = "data.xlsx"; // 这里可以根据需要自定义文件名
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        // 使用EasyExcel写入响应流
        EasyExcel.write(response.getOutputStream(), dataClass)
                .sheet("Sheet 1")
                .doWrite(dataList);
    }
}