package com.itheima.sfbx.sms.feign;

import com.itheima.sfbx.framework.commons.dto.sms.SmsSendRecordVO;
import com.itheima.sfbx.sms.hystrix.SmsSendRecordHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @ClassName SmsSendRecordFegin.java
 * @Description TODO
 */
@FeignClient(value = "sms-web",fallback = SmsSendRecordHystrix.class)
public interface SmsSendRecordFegin {

    /***
     * @description 查询回调结果的记录
     *
     * @return: com.itheima.easy.basic.ResponseResult<java.util.List<com.itheima.easy.vo.sms.SmsSendRecordVO>>
     */
    @PostMapping("/sms-send-record-feign/call-back-sms-send-records")
    List<SmsSendRecordVO> callBackSmsSendRecords();
}
