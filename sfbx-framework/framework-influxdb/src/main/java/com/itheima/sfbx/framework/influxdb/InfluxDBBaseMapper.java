package com.itheima.sfbx.framework.influxdb;

import com.itheima.sfbx.framework.influxdb.anno.Insert;

import java.util.List;

public interface InfluxDBBaseMapper<T> {

    @Insert
    void insertOne(T entity);

    @Insert
    void insertBatch(List<T> entityList);
}
