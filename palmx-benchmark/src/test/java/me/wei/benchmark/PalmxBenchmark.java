package me.wei.benchmark;

import me.wei.ConsumerRun;
import me.wei.service.PalmxService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class PalmxBenchmark {


    @Param(value = {"10", "100", "1000", "10000", "100000"})
    private int param;

    private ConfigurableApplicationContext context;
    private PalmxService palmxService;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(ConsumerRun.class);
        // 获取需要测试的bean
        this.palmxService = (PalmxService) context.getBean("palmxService");
    }

    @TearDown
    public void down() {
        context.close();
    }

    @Benchmark
    public void invoke() {
        palmxService.invoke();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PalmxBenchmark.class.getSimpleName())
                .result(LocalDateTime.now().getMinute() + "-PalmxBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
