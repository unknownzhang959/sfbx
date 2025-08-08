package com.itheima.sfbx.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 负责订单明细、支付渠道管理、支付回调、其它与支付相关的功能对应的启动引导类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class TradeWebStart {

    public static void main(String[] args) {
        SpringApplication.run(TradeWebStart.class, args);
    }
}
