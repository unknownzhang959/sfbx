package com.itheima.sfbx.insurance.service;

import com.itheima.sfbx.insurance.dto.CustomerRelationVO;
import com.itheima.sfbx.insurance.dto.DoInsureVo;
import com.itheima.sfbx.insurance.dto.InsureProcessVO;
import com.itheima.sfbx.insurance.dto.WorryFreeCustomerInfoVO;
import com.itheima.sfbx.insurance.rule.AccessRuleDTO;
import com.itheima.sfbx.insurance.rule.advice.AdviceHealthDTO;

/**
 * IRuleService
 *
 * @author: wgl
 * @describe: 规则引擎service
 * @date: 2022/12/28 10:10
 */
public interface IRuleService {


    /**
     * 省心配规则
     * @param customerInfoVO
     */
    WorryFreeCustomerInfoVO worryFreePairing(WorryFreeCustomerInfoVO customerInfoVO);


    void testRule(String id);

    void testRuleTable(String id);

    /**
     * 保险初筛
     * @param adviceHealthDTO
     * @return
     */
    AdviceHealthDTO submitQuestionChoose(AdviceHealthDTO adviceHealthDTO);

    /**
     * 设置基本属性
     * @param worryFreeCustomerInfoVO
     */
    void buildBaseParams(WorryFreeCustomerInfoVO worryFreeCustomerInfoVO);
}
