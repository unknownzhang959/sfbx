package com.itheima.sfbx.points.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.sfbx.points.mapper.DauTimeMapper;
import com.itheima.sfbx.points.pojo.DauTime;
import com.itheima.sfbx.points.service.IDauTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description：用户活跃数时段服务接口实现类
 */
@Slf4j
@Service
public class DauTimeServiceImpl extends ServiceImpl<DauTimeMapper, DauTime> implements IDauTimeService {

}
