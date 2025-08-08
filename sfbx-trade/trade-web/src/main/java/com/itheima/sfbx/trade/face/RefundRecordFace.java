package com.itheima.sfbx.trade.face;

import com.itheima.sfbx.framework.commons.dto.trade.RefundRecordVO;

import java.util.List;

/**
 * @ClassName TradeFace.java
 * @Description 交易Face接口
 */
public interface RefundRecordFace {


    /***
     * @description 按退款状态查询退款记录
     * @param refundStatus
     * @return
     */
    List<RefundRecordVO> findRefundRecordByRefundStatus(String refundStatus);
}
