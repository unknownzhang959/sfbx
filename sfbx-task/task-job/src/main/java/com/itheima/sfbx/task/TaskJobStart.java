package com.itheima.sfbx.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class TaskJobStart {

    public static void main(String[] args) {
        SpringApplication.run(TaskJobStart.class, args);
    }
}
