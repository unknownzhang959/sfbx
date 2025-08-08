package com.itheima.sfbx.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description：管理后端项目启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class GatewayMgtStart {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMgtStart.class, args);
    }
}
