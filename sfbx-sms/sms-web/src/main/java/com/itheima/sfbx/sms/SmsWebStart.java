package com.itheima.sfbx.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 短信微服务启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class SmsWebStart {

    public static void main(String[] args) {
        SpringApplication.run(SmsWebStart.class, args);
    }
}
