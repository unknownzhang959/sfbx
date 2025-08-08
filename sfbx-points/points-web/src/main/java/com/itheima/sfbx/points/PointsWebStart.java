package com.itheima.sfbx.points;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 数据采集埋点启动类（当前端登录、查看产品等时需要采集数据的话需要启动）
 */
@SpringBootApplication(scanBasePackages = {"com.itheima.sfbx","com.itheima.sfbx.points.mapper"})
public class PointsWebStart {

    public static void main(String[] args) {

        SpringApplication.run(PointsWebStart.class, args);
    }
}
