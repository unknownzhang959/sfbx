package com.itheima.sfbx.framework.outinterface.service;

/**
 * 银行卡业务层
 */
public interface BankCardService {

    /**
     * 银行卡照片Base64图片转换
     * @param base64Image
     * @return
     */
    String bankCardOcr(String base64Image) throws Exception ;
}
