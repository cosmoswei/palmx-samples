package me.wei.benchmark;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import me.wei.service.DemoService;
import me.wei.service.DemoServiceImpl;
import me.wei.service.PalmxService;
import me.xuqu.palmx.locator.DefaultServiceLocator;
import me.xuqu.palmx.net.PalmxServer;
import me.xuqu.palmx.net.netty.NettyServer;
import me.xuqu.palmx.provider.DefaultServiceProvider;
import me.xuqu.palmx.registry.ServiceRegistry;
import me.xuqu.palmx.registry.impl.ZookeeperServiceRegistry;
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
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 10, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
public class PalmxBenchmark {


    @Param(value = {"1", "2", "5", "10", "100"})
    private int param;

    DemoService demoService;

    @Setup
    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
//        server();
        client();
        log.info("启动 init ");
    }

    public void client() {
        DefaultServiceLocator serviceLocator = new DefaultServiceLocator();
        demoService = serviceLocator.lookup(DemoService.class);
    }

    public void server() {
        // 启动一个服务器
        PalmxServer server = new NettyServer();
        new Thread(server::start, "palmx-server").start();
        // 创建单个服务的实现类实例，并将其添加到容器中管理
        String serviceName = PalmxService.class.getName();
        DemoService fooService = new DemoServiceImpl();
        DefaultServiceProvider.getInstance().addService(serviceName, fooService);
        // 将指定服务注册到 Zookeeper
        ServiceRegistry serviceRegistry = new ZookeeperServiceRegistry();
        serviceRegistry.register(serviceName, server.getAddress());
    }

    @TearDown
    public void down() {
    }

    @Benchmark
    public void invoke() {
        demoService.demoSleepSecond(param);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PalmxBenchmark.class.getSimpleName())
                .result(LocalDateTime.now().getMinute() + "-PalmxBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
