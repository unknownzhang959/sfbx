package com.itheima.sfbx.insurance.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.itheima.sfbx.framework.commons.basic.ResponseResult;
import com.itheima.sfbx.framework.commons.utils.ResponseResultBuild;
import com.itheima.sfbx.insurance.dto.SafeguardVO;
import com.itheima.sfbx.insurance.service.ISafeguardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "保障项")
@RestController
@RequestMapping("/safeguard")
public class SafeguardController {

    @Autowired
    private ISafeguardService safeguardService;

    /**
     * 分页查询
     * @param safeguardVO 查询条件
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    /*swagger 注解说明
    @ApiImplicitParams 表示能接受的参数列表。
    @ApiImplicitParam 表示具体的参数说明和属性
    @ApiOperationSupport 表示swagger的扩展属性，可以对指定参数做扩展补充
    includeParameters 表示参数safeguardVO中的属性，可以指定哪些属性需要swagger展示
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO"),
            @ApiImplicitParam(paramType = "path",name = "pageNum",value = "页码",example = "1",dataType = "Integer"),
            @ApiImplicitParam(paramType = "path",name = "pageSize",value = "每页条数",example = "10",dataType = "Integer")
    })
    @ApiOperationSupport(includeParameters = {"safeguardVO.safeguardType","safeguardVO.safeguardKey","safeguardVO.safeguardVal","safeguardVO.remake"})
    @PostMapping("/page/{pageNum}/{pageSize}")
    public ResponseResult<Page<SafeguardVO>> findSafeguardVOPage(
            @RequestBody SafeguardVO safeguardVO,
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize
    ){
        Page<SafeguardVO> page = safeguardService.findPage(safeguardVO, pageNum, pageSize);
        return ResponseResultBuild.successBuild(page);
    }
    /**
     * 新增保险项
     * @param safeguardVO 保险项VO对象
     * @return 保险项VO对象
     */
    @ApiOperation(value = "新增保险项",notes = "新增保险项")
    @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters ={"safeguardVO.safeguardType","safeguardVO.safeguardKey","safeguardVO.safeguardVal","safeguardVO.remake", "safeguardVO.dataState", "safeguardVO.sortNo"})
    @PutMapping
    public ResponseResult<SafeguardVO> saveSafeguardVO(@RequestBody SafeguardVO safeguardVO){
        SafeguardVO save = safeguardService.save(safeguardVO);
        return ResponseResultBuild.successBuild(save);
    }
    /**
     * 修改保险项
     * @param safeguardVO 保险项VO对象
     * @return 保险项VO对象
     */
    @ApiOperation(value = "修改保险项",notes = "修改保险项")
    @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters = {"safeguardVO.id","safeguardVO.safeguardType","safeguardVO.safeguardKey","safeguardVO.safeguardVal","safeguardVO.remake", "safeguardVO.dataState", "safeguardVO.sortNo"})
    @PatchMapping
    public ResponseResult<Boolean> updateSafeguardVO(@RequestBody SafeguardVO safeguardVO){
        Boolean update = safeguardService.update(safeguardVO);
        return ResponseResultBuild.successBuild(update);
    }
    /**
     * 删除保险项
     * @param safeguardVO 保险项VO对象
     * @return 删除结果
     */
    @ApiOperation(value = "删除保险项",notes = "删除保险项")
    @ApiImplicitParam(name = "safeguardVO",value = "VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters = {"safeguardVO.checkedIds"})
    @DeleteMapping
    public ResponseResult<Boolean> deleteSafeguardVO(@RequestBody SafeguardVO safeguardVO){
        Boolean delete = safeguardService.delete(safeguardVO.getCheckedIds());
        return ResponseResultBuild.successBuild(delete);
    }
    /***
     * @description 多条件查询保障项列表
     * @param safeguardVO 保障项VO对象
     * @return List<SafeguardVO>
     */
    @PostMapping("list")
    @ApiOperation(value = "多条件查询保障项列表",notes = "多条件查询保障项列表")
    @ApiImplicitParam(name = "safeguardVO",value = "保障项VO对象",required = true,dataType = "SafeguardVO")
    @ApiOperationSupport(includeParameters = {"safeguardVO.dataState","safeguardVO.safeguardKey","safeguardVO.safeguardKeyName","safeguardVO.safeguardVal","safeguardVO.safeguardType","safeguardVO.sortNo","safeguardVO.remake"})
    public ResponseResult<List<SafeguardVO>> safeguardList(@RequestBody SafeguardVO safeguardVO) {
        List<SafeguardVO> safeguardVOList = safeguardService.findList(safeguardVO);
        return ResponseResultBuild.successBuild(safeguardVOList);
    }

    /***
     * @description 按safeguardKey查询SafeguardVO
     * @param safeguardKey 保障项key
     * @return SafeguardVO
     */
    @PostMapping("safeguard-key/{safeguardKey}")
    @ApiOperation(value = "保障项key查询SafeguardVO",notes = "保障项key查询SafeguardVO")
    @ApiImplicitParam(paramType = "path",name = "safeguardKey",value = "保障项key",dataType = "String")
    public ResponseResult<SafeguardVO> findBySafeguardKey(@PathVariable("safeguardKey") String safeguardKey) {
        SafeguardVO safeguardVO = safeguardService.findBySafeguardKey(safeguardKey);
        return ResponseResultBuild.successBuild(safeguardVO);
    }

}