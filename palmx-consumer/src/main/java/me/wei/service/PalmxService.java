package me.wei.service;

import me.xuqu.palmx.spring.PalmxClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PalmxService {

    @PalmxClient
    private DemoService demoService;
    @PalmxClient
    private TestService testService;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public String invokeDemo() {
        long start = System.currentTimeMillis();
        String res = demoService.demoInvoke();
        long end = System.currentTimeMillis();
        return "method = invokeDemo, res = " + res + ", duration = " + (end - start);
    }


    public String loopInvokeDemoAsync(int loopCount) {
        // 使用 CountDownLatch 确保所有任务完成
        CountDownLatch latch = new CountDownLatch(loopCount);
        long start = System.currentTimeMillis();
        String res = null;
        for (int i = 0; i < loopCount; i++) {
            executorService.submit(() -> {
                try {
                    demoService.demoInvoke();
                } finally {
                    latch.countDown(); // 每完成一个任务，减一
                }
            });
        }
        // 等待所有任务完成
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        return "method = invokeDemo, loop cnt = " + loopCount + ", duration = " + (end - start);
    }

    public String loopInvokeDemo(int loopCount) {
        long start = System.currentTimeMillis();
        String res = null;
        for (int i = 0; i < loopCount; i++) {
            res = demoService.demoInvoke();
        }
        long end = System.currentTimeMillis();
        return "method = invokeDemo, res = " + res + ", duration = " + (end - start);
    }

    public String invokeTest() {
        return "invokeTest = " + testService.testSleepSecond(1L);
    }
}
