package com.itheima.sfbx.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后端管理界面中几乎所有管理功能对应的启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class InsuranceMgtStart {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceMgtStart.class);
    }
}
