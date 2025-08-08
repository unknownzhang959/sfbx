package com.itheima.sfbx.sms.hystrix;

import com.itheima.sfbx.framework.commons.dto.sms.SmsSendRecordVO;
import com.itheima.sfbx.sms.feign.SmsSendRecordFegin;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SmsSendRecordHystrix.java
 * @Description TODO
 */
@Component
public class SmsSendRecordHystrix implements SmsSendRecordFegin {


    @Override
    public List<SmsSendRecordVO> callBackSmsSendRecords() {
        return null;
    }
}
