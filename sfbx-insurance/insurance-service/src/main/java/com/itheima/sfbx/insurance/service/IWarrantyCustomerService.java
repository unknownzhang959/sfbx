package com.itheima.sfbx.insurance.service;

import com.itheima.sfbx.insurance.dto.WarrantyApplicantVO;
import com.itheima.sfbx.insurance.pojo.WarrantyInsured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName WarrantyApplicantService.java
 * @Description 合同客户相关
 */
public interface IWarrantyCustomerService {

    /***
     * @description 按保单号查询投保人信息
     * @param warrantyNo
     * @return
     */
    WarrantyApplicantVO findWarrantyApplicant(String warrantyNo);

    /***
     * @description 按保单号查询被投保人信息
     * @param warrantyNo
     * @return
     */
    List<WarrantyInsured> findWarrantyInsured(String warrantyNo);
}
