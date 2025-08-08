package com.itheima.sfbx.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.sfbx.sms.pojo.SmsSendRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description：发送记录表Mapper接口
 */
@Mapper
public interface SmsSendRecordMapper extends BaseMapper<SmsSendRecord> {

}
