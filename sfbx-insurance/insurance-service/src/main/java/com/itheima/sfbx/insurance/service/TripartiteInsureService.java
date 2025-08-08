package com.itheima.sfbx.insurance.service;

import com.itheima.sfbx.dto.ResponseDTO;
import com.itheima.sfbx.framework.commons.dto.external.TripartiteInsureDTO;
import com.itheima.sfbx.insurance.dto.WarrantyVO;

/**
 * 三方接口提供的Service
 */
public interface TripartiteInsureService {

    /***
     * @description 三方投保接口
     *
     * @param warrantyVO
     * @return
     */
    ResponseDTO insure(WarrantyVO warrantyVO);
}
