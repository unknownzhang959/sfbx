package com.itheima.sfbx.trade.utils;

import com.itheima.sfbx.trade.client.wechat.response.BasicResponse;

/**
 * @ClassName ResponseChecker.java
 * @Description 微信结果检查
 */
public class ResponseChecker {

    public static boolean success(BasicResponse response) {
        String code = response.getCode();
        return code.equals("200");
    }

}
