package com.itheima.sfbx.trade.face.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itheima.sfbx.framework.commons.constant.basic.SuperConstant;
import com.itheima.sfbx.framework.commons.dto.trade.RefundRecordVO;
import com.itheima.sfbx.framework.commons.utils.BeanConv;
import com.itheima.sfbx.trade.face.RefundRecordFace;
import com.itheima.sfbx.trade.pojo.RefundRecord;
import com.itheima.sfbx.trade.service.IRefundRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName TradeFace.java
 * @Description 交易Face接口
 */
@Slf4j
@Component
public class ReFundRecordFaceImpl implements RefundRecordFace {

    @Autowired
    IRefundRecordService refundRecordService;

    /***
     * @description 按退款状态查询退款记录
     * @param refundStatus
     * @return
     */
    @Override
    public List<RefundRecordVO> findRefundRecordByRefundStatus(String refundStatus) {
        LambdaQueryWrapper<RefundRecord> wrapper = Wrappers.<RefundRecord>lambdaQuery()
                .eq(RefundRecord::getRefundStatus, refundStatus)
                .eq(RefundRecord::getDataState, SuperConstant.DATA_STATE_0);
        List<RefundRecord> refundRecordList = refundRecordService.list(wrapper);
        return BeanConv.toBeanList(refundRecordList,RefundRecordVO.class);
    }

}
