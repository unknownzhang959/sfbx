package com.itheima.sfbx.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 负责系统所有的消息队列监听 对应启动类
 */
@SpringBootApplication(scanBasePackages = "com.itheima.sfbx")
public class TaskListenerStart {

    public static void main(String[] args) {
        SpringApplication.run(TaskListenerStart.class);
    }
}
