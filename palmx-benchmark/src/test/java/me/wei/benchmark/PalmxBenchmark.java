package me.wei.benchmark;

import lombok.extern.slf4j.Slf4j;
import me.wei.service.PalmxService;
import me.xuqu.palmx.locator.DefaultServiceLocator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@Threads(5)
@Fork(5)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PalmxBenchmark {

    @Param(value = {"100", "1000", "10000", "20000"})
    private int param;

    private PalmxService demoService;

    private DefaultServiceLocator serviceLocator;

    @Setup
    public void init() throws InterruptedException {
        client();
        log.info("启动 init ");
        Thread.sleep(2000);
    }

    public void client() {
        serviceLocator = new DefaultServiceLocator();
        demoService = serviceLocator.lookup(PalmxService.class);
    }

    public void server() {
    }

    @TearDown
    public void down() {
        serviceLocator.shutdown();
    }

    @Benchmark
    public void invoke() {
        int loop = param;
        for (int i = 0; i < loop; i++) {
            String res = demoService.demoInvoke();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PalmxBenchmark.class.getSimpleName())
                .result("palmx-benchmark/result/"
                        + LocalDateTime.now().getMinute()
                        + "-palmxBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
