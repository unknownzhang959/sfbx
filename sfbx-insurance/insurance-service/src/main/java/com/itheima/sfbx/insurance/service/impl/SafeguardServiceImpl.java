package com.itheima.sfbx.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.sfbx.framework.commons.constant.basic.SuperConstant;
import com.itheima.sfbx.insurance.pojo.Safeguard;
import com.itheima.sfbx.insurance.mapper.SafeguardMapper;
import com.itheima.sfbx.insurance.service.ISafeguardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.sfbx.insurance.constant.SafeguardCacheConstant;
import com.itheima.sfbx.insurance.dto.SafeguardVO;
import com.itheima.sfbx.insurance.enums.SafeguardEnum;
import com.itheima.sfbx.framework.commons.utils.BeanConv;
import com.itheima.sfbx.framework.commons.utils.EmptyUtil;
import com.itheima.sfbx.framework.commons.utils.ExceptionsUtil;
import com.itheima.sfbx.framework.commons.exception.ProjectException;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SafeguardServiceImpl extends ServiceImpl<SafeguardMapper, Safeguard> implements ISafeguardService {
    @Override
    public Page<SafeguardVO> findPage(SafeguardVO safeguardVO, int pageNum, int pageSize) {
        Page<Safeguard> safeguardPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Safeguard> queryWrapper = new LambdaQueryWrapper<>();
        //根据保障项编号
        queryWrapper.eq(!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardKey()),
                Safeguard::getSafeguardKey, safeguardVO.getSafeguardKey());
        //根据保障项名称
        queryWrapper.like(!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardKeyName()),
                Safeguard::getSafeguardKeyName, safeguardVO.getSafeguardKeyName());
        //状态
        queryWrapper.eq(!EmptyUtil.isNullOrEmpty(safeguardVO.getDataState()),
                Safeguard::getDataState, safeguardVO.getDataState());
        //按照创建时间降序排
        queryWrapper.orderByDesc(Safeguard::getCreateTime);

        Page<Safeguard> resultPage = page(safeguardPage, queryWrapper);

        //转换为VO
        Page<SafeguardVO> safeguardVOPage = BeanConv.toPage(resultPage, SafeguardVO.class);
        return safeguardVOPage;
    }

    @Override
    public SafeguardVO findById(String safeguardId) {
        return null;
    }

    @Override
    public SafeguardVO save(SafeguardVO safeguardVO) {
        Safeguard safeguard = BeanConv.toBean(safeguardVO, Safeguard.class);
        boolean save = save(safeguard);
        if(!save){
            throw new RuntimeException("保存失败");
        }
        return BeanConv.toBean(safeguard, SafeguardVO.class);
    }

    @Override
    public Boolean update(SafeguardVO safeguardVO) {
        Safeguard safeguard = BeanConv.toBean(safeguardVO, Safeguard.class);
        boolean flag = updateById(safeguard);
        if (!flag){
            throw new RuntimeException("更新失败");
        }
        return flag;
    }

    @Override
    public Boolean delete(String[] checkedIds) {
        List<Long> collect = Arrays.asList(checkedIds).stream().map(Long::new).collect(Collectors.toList());
        boolean flag = removeByIds(collect);
        if (!flag){
            throw new RuntimeException("删除失败");
        }
        return flag;
    }

    @Override
    public List<SafeguardVO> findList(SafeguardVO safeguardVO) {
        return null;
    }

    @Override
    public List<SafeguardVO> findShowPageItemByKey(List<String> safeguardKeyList) {
        return null;
    }

    @Override
    public SafeguardVO findBySafeguardKey(String safeguardKey) {
        return null;
    }

}
