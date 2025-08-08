package com.itheima.sfbx.insurance.mapper;

import com.itheima.sfbx.insurance.dto.SickVO;
import com.itheima.sfbx.insurance.pojo.Sick;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description：疾病表Mapper接口
 */
@Mapper
public interface SickMapper extends BaseMapper<Sick> {

}
