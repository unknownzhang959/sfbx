package com.itheima.sfbx.wenxin.chains;


import com.itheima.sfbx.wenxin.dto.ParamsDTO;
import com.itheima.sfbx.wenxin.dto.RequestResultDTO;

/**
 * 问答统一模板类
 */
public interface QuestionService {

    /**
     * 向百度文心一言发送请求
     */
    public RequestResultDTO doRequest(ParamsDTO params);

}
