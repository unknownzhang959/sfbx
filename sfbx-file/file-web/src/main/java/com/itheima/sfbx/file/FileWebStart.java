package com.itheima.sfbx.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 文件上传处理微服务
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class FileWebStart {

    public static void main(String[] args) {
        SpringApplication.run(FileWebStart.class, args);
    }
}
