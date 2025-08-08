package com.itheima.sfbx.insurance.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.itheima.sfbx.framework.commons.basic.ResponseResult;
import com.itheima.sfbx.framework.commons.dto.dict.DataDictVO;
import com.itheima.sfbx.framework.commons.utils.ResponseResultBuild;
import com.itheima.sfbx.framework.commons.utils.SubjectContent;
import com.itheima.sfbx.insurance.dto.*;
import com.itheima.sfbx.insurance.rule.advice.AdviceHealthDTO;
import com.itheima.sfbx.insurance.service.IInsuranceSievingService;
import com.itheima.sfbx.insurance.service.ISickSearchRecordService;
import com.itheima.sfbx.insurance.service.ISickService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SeekAdviceController.java
 * @Description 健康咨询
 */
@Slf4j
@Api(tags = "健康咨询，保险初筛")
@RestController
@RequestMapping("seek-advice")
public class SeekAdviceController {

    @Autowired
    private IInsuranceSievingService insuranceSievingService;

    /**
     * @description 分页查询保险问卷
     */
    @PostMapping("page/{pageNum}/{pageSize}")
    @ApiOperation(value = "分页查询保险问卷",notes = "分页查询保险问卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "insuranceSievingVO",value = "筛选项VO对象",required = true,dataType = "InsuranceSievingVO"),
            @ApiImplicitParam(paramType = "path",name = "pageNum",value = "页码",example = "1",dataType = "Integer"),
            @ApiImplicitParam(paramType = "path",name = "pageSize",value = "每页条数",example = "10",dataType = "Integer")
    })
    public ResponseResult<Page<InsuranceSievingVO>> findInsuranceSievingPage(
            @RequestBody InsuranceSievingVO insuranceSievingVO,
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize) {
        Page<InsuranceSievingVO> page = insuranceSievingService.findPage(insuranceSievingVO, pageNum, pageSize);
        return ResponseResultBuild.successBuild(page);
    }

}