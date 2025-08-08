package com.itheima.sfbx.points.hystrix;

import com.itheima.sfbx.framework.commons.dto.log.LogBusinessVO;
import com.itheima.sfbx.points.feign.BusinessLogFeign;

/**
 * @ClassName SaleReportFeignHystrix.java
 * @Description TODO
 */
public class SaleReportFeignHystrix implements BusinessLogFeign {


    @Override
    public Boolean createBusinessLog(LogBusinessVO logBusinessVO) {
        return null;
    }
}
