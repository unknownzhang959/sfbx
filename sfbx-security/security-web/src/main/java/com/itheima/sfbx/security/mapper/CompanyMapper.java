package com.itheima.sfbx.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.sfbx.security.pojo.Company;
import com.itheima.sfbx.security.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description：企业表Mapper接口
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

}
