package com.itheima.sfbx.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description：小程序项目启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class GatewayAppStart {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAppStart.class, args);
    }
}
