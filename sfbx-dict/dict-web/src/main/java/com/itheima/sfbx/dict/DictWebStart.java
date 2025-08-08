package com.itheima.sfbx.dict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 后台管理中的 系统管理-数字字典
 */
@SpringBootApplication(scanBasePackages = {"com.itheima.sfbx"})
public class DictWebStart {

    public static void main(String[] args) {
        SpringApplication.run(DictWebStart.class, args);
    }
}
