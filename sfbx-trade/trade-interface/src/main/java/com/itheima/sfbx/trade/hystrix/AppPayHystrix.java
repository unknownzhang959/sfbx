package com.itheima.sfbx.trade.hystrix;

import com.itheima.sfbx.trade.feign.AppPayFeign;
import com.itheima.sfbx.framework.commons.dto.trade.TradeVO;
import org.springframework.stereotype.Component;

/**
 * @ClassName AppPayHystrix.java
 * @Description TODO
 */
@Component
public class AppPayHystrix implements AppPayFeign {


    @Override
    public TradeVO appTrade(TradeVO tradeVO) {
        return null;
    }
}
