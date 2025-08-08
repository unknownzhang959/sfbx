package com.itheima.sfbx.points.hystrix;

import com.itheima.sfbx.framework.commons.dto.log.LogBusinessVO;
import com.itheima.sfbx.points.feign.BusinessLogFeign;

/**
 * @ClassName BusinessLogHystrix.java
 * @Description TODO
 */
public class BusinessLogHystrix implements BusinessLogFeign {

    @Override
    public Boolean createBusinessLog(LogBusinessVO logBusinessVO) {
        return null;
    }
}
