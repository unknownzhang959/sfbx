package com.itheima.sfbx.trade.hystrix;

import com.itheima.sfbx.trade.feign.PagePayFeign;
import com.itheima.sfbx.framework.commons.dto.trade.TradeVO;
import org.springframework.stereotype.Component;

/**
 * @ClassName PagePayHystrix.java
 * @Description TODO
 */
@Component
public class PagePayHystrix implements PagePayFeign {

    @Override
    public TradeVO pageTrade(TradeVO tradeVO) {
        return null;
    }
}
