package com.itheima.sfbx.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 小程序端对于保险的启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class InsuranceAppStart {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceAppStart.class);
    }
}