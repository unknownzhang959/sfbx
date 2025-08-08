package com.itheima.sfbx.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 前后端都需要使用到的登录认证中心
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
@EnableDiscoveryClient
public class SecurityOauthStart {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauthStart.class);
    }
}
