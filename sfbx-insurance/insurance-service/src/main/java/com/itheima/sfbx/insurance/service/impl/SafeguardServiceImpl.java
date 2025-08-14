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
    @Cacheable(value = SafeguardCacheConstant.PAGE,key ="#pageNum+'-'+#pageSize+'-'+#safeguardVO.hashCode()")
    public Page<SafeguardVO> findPage(SafeguardVO safeguardVO, int pageNum, int pageSize) {
        Page<Safeguard> safeguardPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Safeguard> queryWrapper = queryWrapper(safeguardVO);
        Page<Safeguard> resultPage = page(safeguardPage, queryWrapper);

        //转换为VO
        Page<SafeguardVO> safeguardVOPage = BeanConv.toPage(resultPage, SafeguardVO.class);
        return safeguardVOPage;
    }

    @Override
    @Cacheable(value = SafeguardCacheConstant.BASIC,key ="#safeguardId")
    public SafeguardVO findById(String safeguardId) {
        try {
            //执行查询
            return BeanConv.toBean(getById(safeguardId),SafeguardVO.class);
        }catch (Exception e){
            log.error("保障项单条查询异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.FIND_ONE_FAIL);
        }
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = SafeguardCacheConstant.LIST,allEntries = true),
            @CacheEvict(value = SafeguardCacheConstant.PAGE,allEntries = true)
    },
            put = {@CachePut(value = SafeguardCacheConstant.BASIC,key = "#result.id")}
    )
    public SafeguardVO save(SafeguardVO safeguardVO) {
        Safeguard safeguard = BeanConv.toBean(safeguardVO, Safeguard.class);
        boolean save = save(safeguard);
        if(!save){
            throw new ProjectException(SafeguardEnum.SAVE_FAIL);
        }
        return BeanConv.toBean(safeguard, SafeguardVO.class);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = SafeguardCacheConstant.LIST,allEntries = true),
            @CacheEvict(value = SafeguardCacheConstant.PAGE,allEntries = true),
            @CacheEvict(value = SafeguardCacheConstant.BASIC,key = "#safeguardVO.id")
    }
    )
    public Boolean update(SafeguardVO safeguardVO) {
        Safeguard safeguard = BeanConv.toBean(safeguardVO, Safeguard.class);
        boolean flag = updateById(safeguard);
        if (!flag){
            throw new ProjectException(SafeguardEnum.UPDATE_FAIL);
        }
        return flag;
    }

    @Override
    @Caching(evict = {@CacheEvict(value = SafeguardCacheConstant.LIST,allEntries = true),
            @CacheEvict(value = SafeguardCacheConstant.PAGE,allEntries = true),
            @CacheEvict(value = SafeguardCacheConstant.BASIC,allEntries = true)
    })
    public Boolean delete(String[] checkedIds) {
        List<Long> collect = Arrays.asList(checkedIds).stream().map(Long::new).collect(Collectors.toList());
        boolean flag = removeByIds(collect);
        if (!flag){
            throw new ProjectException(SafeguardEnum.DEL_FAIL);
        }
        return flag;
    }

    @Override
    @Cacheable(value = SafeguardCacheConstant.LIST,key ="#safeguardVO.hashCode()")
    public List<SafeguardVO> findList(SafeguardVO safeguardVO) {
        try {
            //构建查询条件
            QueryWrapper<Safeguard> queryWrapper = queryWrapper(safeguardVO);
            //执行列表查询
            List<SafeguardVO> safeguardVOs = BeanConv.toBeanList(list(queryWrapper),SafeguardVO.class);
            return safeguardVOs;
        }catch (Exception e){
            log.error("保障项列表查询异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.LIST_FAIL);
        }
    }


    @Override
    @Cacheable(value = SafeguardCacheConstant.LIST,key ="#safeguardKeyList.hashCode()")
    public List<SafeguardVO> findShowPageItemByKey(List<String> safeguardKeyList) {
        try {
            //构建查询条件
            LambdaQueryWrapper<Safeguard> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Safeguard::getSafeguardKey,safeguardKeyList);
            queryWrapper.eq(Safeguard::getDataState, SuperConstant.DATA_STATE_0);
            //执行列表查询
            List<SafeguardVO> safeguardVOs = BeanConv.toBeanList(list(queryWrapper),SafeguardVO.class);
            return safeguardVOs;
        }catch (Exception e){
            log.error("保障项列表查询异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.LIST_FAIL);
        }
    }

    @Override
    @Cacheable(value = SafeguardCacheConstant.BASIC,key ="#safeguardKey")
    public SafeguardVO findBySafeguardKey(String safeguardKey) {
        try {
            //构建查询条件
            LambdaQueryWrapper<Safeguard> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Safeguard::getSafeguardKey,safeguardKey);
            queryWrapper.eq(Safeguard::getDataState, SuperConstant.DATA_STATE_0);
            //执行列表查询
            return BeanConv.toBean(getOne(queryWrapper),SafeguardVO.class);
        }catch (Exception e){
            log.error("保障项列表查询异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.FIND_ONE_FAIL);
        }
    }
    /***
     * @description 保障项多条件组合
     * @param safeguardVO 保障项
     * @return QueryWrapper查询条件
     */
    private QueryWrapper<Safeguard> queryWrapper(SafeguardVO safeguardVO){
        QueryWrapper<Safeguard> queryWrapper = new QueryWrapper<>();
        //条例键查询
        if (!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardKey())) {
            queryWrapper.lambda().eq(Safeguard::getSafeguardKey,safeguardVO.getSafeguardKey());
        }
        //条例键名称查询
        if (!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardKeyName())) {
            queryWrapper.lambda().eq(Safeguard::getSafeguardKeyName,safeguardVO.getSafeguardKeyName());
        }
        //条例值查询
        if (!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardVal())) {
            queryWrapper.lambda().eq(Safeguard::getSafeguardVal,safeguardVO.getSafeguardVal());
        }
        //排序查询
        if (!EmptyUtil.isNullOrEmpty(safeguardVO.getSortNo())) {
            queryWrapper.lambda().eq(Safeguard::getSortNo,safeguardVO.getSortNo());
        }
        //备注查询
        if (!EmptyUtil.isNullOrEmpty(safeguardVO.getRemake())) {
            queryWrapper.lambda().eq(Safeguard::getRemake,safeguardVO.getRemake());
        }
        //状态查询
        if (!EmptyUtil.isNullOrEmpty(safeguardVO.getDataState())) {
            queryWrapper.lambda().eq(Safeguard::getDataState,safeguardVO.getDataState());
        }
        //按创建时间降序
        queryWrapper.lambda().orderByDesc(Safeguard::getCreateTime);
        return queryWrapper;
    }

}
