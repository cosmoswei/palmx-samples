package me.wei;

import me.xuqu.palmx.spring.EnablePalmx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePalmx
public class ConsumerRun {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRun.class, args);
    }

}
