package com.itheima.sfbx.insurance.handler.impl;

import com.itheima.sfbx.framework.commons.constant.insure.InsureConstant;
import com.itheima.sfbx.insurance.dto.CustomerRelationVO;
import com.itheima.sfbx.insurance.dto.DoInsureVo;
import com.itheima.sfbx.insurance.handler.InsureHandler;
import com.itheima.sfbx.insurance.service.ICustomerRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName AccidentInsureHandler.java
 * @Description 保障型意外类：保险投保、创建订单、保费计算
 */
@Service
public class AccidentInsureHandler extends SafeguardInsureHandler implements InsureHandler {
    @Resource
    ICustomerRelationService customerRelationService;
    @Override
    public String doPremium(DoInsureVo doInsureVo) {
        //查询被投保人
        CustomerRelationVO relationVO = customerRelationService.findById(doInsureVo.getCustomerRelationIds().get(0));
        if (relationVO != null) {
            BigDecimal income = relationVO.getIncome();
            //高于100万或者没有填写的人直接抛出异常
            if (income == null || income.compareTo(new BigDecimal(1000000)) > 0) {
                throw new RuntimeException("杀手不能投保意外险！");
            }
        }

        return super.doPremium(doInsureVo);
    }

}
