package com.itheima.sfbx.points.hystrix;

import com.itheima.sfbx.points.feign.CustomerReportFeign;

/**
 * @ClassName CustomerReportHystrix.java
 * @Description TODO
 */
public class CustomerReportHystrix implements CustomerReportFeign {

    @Override
    public Boolean dnuJob(String reportTime) {
        return null;
    }

    @Override
    public Boolean dnuCityJob(String reportTime) {
        return null;
    }

    @Override
    public Boolean dauJob(String reportTime) {
        return null;
    }

    @Override
    public Boolean dauTimeJob(String reportTime) {
        return null;
    }

    @Override
    public Boolean dpvJob(String reportTime) {
        return null;
    }

    @Override
    public Boolean duvJob(String reportTime) {
        return null;
    }

    @Override
    public Boolean dauRangeJob(String reportTime) {
        return null;
    }
}
