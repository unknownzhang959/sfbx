package com.itheima.sfbx.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.sfbx.security.pojo.AuthChannel;
import com.itheima.sfbx.security.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description：授权三方表Mapper接口
 */
@Mapper
public interface AuthChannelMapper extends BaseMapper<AuthChannel> {

}
