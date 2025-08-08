package com.itheima.sfbx.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 后台管理中用户对应的基础数据，包括登录的用户等信息；所以如果要登录的话；该微服务需要启动
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class SecurityWebStart {

    public static void main(String[] args) {
        SpringApplication.run(SecurityWebStart.class);
    }
}
