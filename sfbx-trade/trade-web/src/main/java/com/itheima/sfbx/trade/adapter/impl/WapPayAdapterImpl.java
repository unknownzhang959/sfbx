package com.itheima.sfbx.trade.adapter.impl;

import com.itheima.sfbx.trade.adapter.WapPayAdapter;
import com.itheima.sfbx.trade.handler.WapPayHandler;
import com.itheima.sfbx.framework.commons.constant.trade.TradeConstant;
import com.itheima.sfbx.framework.commons.utils.RegisterBeanHandler;
import com.itheima.sfbx.framework.commons.dto.trade.TradeVO;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WapPayAdapter.java
 * @Description 手机网页支付适配实现
 */
@Slf4j
@Component
public class WapPayAdapterImpl implements WapPayAdapter {

    @Autowired
    RegisterBeanHandler registerBeanHandler;

    @Autowired
    RedissonClient redissonClient;

    private static Map<String,String> wapPayHandlers =new HashMap<>();

    static {
        wapPayHandlers.put(TradeConstant.TRADE_CHANNEL_ALI_PAY,"aliWapPayHandler");
        wapPayHandlers.put(TradeConstant.TRADE_CHANNEL_WECHAT_PAY,"wechatWapPayHandler");
    }

    @Override
    public TradeVO wapTrade(TradeVO tradeVO) {
        //2、从IOC容器中找到WapPayHandler实现
        String wapPayHandlerString = wapPayHandlers.get(tradeVO.getTradeChannel());
        WapPayHandler wapPayHandler = registerBeanHandler.getBean(wapPayHandlerString, WapPayHandler.class);
        //3、wapTrade支付交易处理
        TradeVO tradeVOResult = wapPayHandler.wapTrade(tradeVO);
        return tradeVOResult;
    }
}
