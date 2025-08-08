package com.itheima.sfbx.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.sfbx.dict.pojo.Places;
import com.itheima.sfbx.framework.commons.constant.dict.PlacesCacheConstant;
import com.itheima.sfbx.framework.commons.dto.dict.PlacesVO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Description：地方表 服务类
 */
public interface IPlacesService extends IService<Places> {

    /***
     * @description 查询下级
     * @param parentId
     * @return:  List<Places>
     */
    List<PlacesVO> findPlacesVOListByParentId(Long parentId);

}
