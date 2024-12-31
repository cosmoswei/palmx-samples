package me.wei.benchmark;

import lombok.extern.slf4j.Slf4j;
import me.wei.ProviderRun;
import me.wei.service.DemoService;
import me.wei.service.PalmxService;
import me.wei.service.impl.DemoServiceImpl;
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
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PalmxBenchmark {

    @Param(value = {"100", "1000", "10000"})
    private int param;

    DemoService demoService;

    DefaultServiceLocator serviceLocator;

    private ConfigurableApplicationContext context;

    @Setup
    public void init() throws InterruptedException {
//        server();
        client();
        log.info("启动 init ");
        Thread.sleep(2000);
    }

    public void client() {
        serviceLocator = new DefaultServiceLocator();
        demoService = serviceLocator.lookup(DemoService.class);
    }

    public void server() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        context = SpringApplication.run(ProviderRun.class);

        // 启动一个服务器
//        PalmxServer server = new NettyServer();
//        new Thread(server::start, "palmx-server").start();
//        // 创建单个服务的实现类实例，并将其添加到容器中管理
//        String serviceName = PalmxService.class.getName();
//        DemoService fooService = new DemoServiceImpl();
//        DefaultServiceProvider.getInstance().addService(serviceName, fooService);
//        // 将指定服务注册到 Zookeeper
//        ServiceRegistry serviceRegistry = new ZookeeperServiceRegistry();
//        serviceRegistry.register(serviceName, server.getAddress());
    }

    @TearDown
    public void down() {
        serviceLocator.shutdown();
    }

    @Benchmark
    public void invoke() {
        long start = System.currentTimeMillis();
        int loop = param;
        for (int i = 0; i < loop; i++) {
            String res = demoService.demoInvoke();
        }
        long end = System.currentTimeMillis();
        System.out.println(loop + "：time = " + (end - start) + " ms");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PalmxBenchmark.class.getSimpleName())
                .result("palmx-benchmark/result/"
                        + LocalDateTime.now().getMinute()
                        + "-PalmxBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
