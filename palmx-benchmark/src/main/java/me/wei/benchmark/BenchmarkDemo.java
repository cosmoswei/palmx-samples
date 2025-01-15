package me.wei.benchmark;

import me.wei.service.PalmxService;
import me.wei.service.impl.PalmxServiceImpl;
import me.xuqu.palmx.locator.DefaultServiceLocator;
import me.xuqu.palmx.net.PalmxServer;
import me.xuqu.palmx.net.netty.NettyServer;
import me.xuqu.palmx.provider.DefaultServiceProvider;
import me.xuqu.palmx.registry.ServiceRegistry;
import me.xuqu.palmx.registry.impl.ZookeeperServiceRegistry;

public class BenchmarkDemo {

    PalmxService demoService;

    public void init() {
        // 这里的WebApplication.class是项目里的spring boot启动类
        server();
        client();
    }

    public void client() {
        DefaultServiceLocator serviceLocator = new DefaultServiceLocator();
        demoService = serviceLocator.lookup(PalmxService.class);
    }


    public void server() {
        // 启动一个服务器
        PalmxServer server = new NettyServer();
        new Thread(server::start, "palmx-server").start();
        // 创建单个服务的实现类实例，并将其添加到容器中管理
        String serviceName = PalmxService.class.getName();
        PalmxService fooService = new PalmxServiceImpl();
        DefaultServiceProvider.getInstance().addService(serviceName, fooService);
        // 将指定服务注册到 Zookeeper
        ServiceRegistry serviceRegistry = new ZookeeperServiceRegistry();
        serviceRegistry.register(serviceName, server.getAddress());
    }

    public void invoke() {
        for (int i = 0; i < 30; i++) {
            String res = demoService.demoInvoke();
            System.out.println(i + " res = " + res);
        }
        long start = System.currentTimeMillis();

        int loop = 10000;
        for (int i = 0; i < loop; i++) {
            String res = demoService.demoInvoke();
        }
        long end = System.currentTimeMillis();
        System.out.println(loop + "：time = " + (end - start) / 1000 + " s");
    }

    public static void main(String[] args) throws InterruptedException {
        BenchmarkDemo demo = new BenchmarkDemo();
        demo.client();
        Thread.sleep(3000);
        demo.invoke();
    }
}
