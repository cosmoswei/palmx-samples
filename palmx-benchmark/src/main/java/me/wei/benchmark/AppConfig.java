package me.wei.benchmark;

import me.xuqu.palmx.spring.EnablePalmx;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnablePalmx
@Configuration
@ComponentScan(basePackages = "me.xuqu.palmx")
public class AppConfig {
}
