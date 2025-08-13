package com.itheima.sfbx.insurance.pojo;

import com.itheima.sfbx.framework.mybatisplus.basic.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Description：浏览记录
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tab_browsing_history")
@ApiModel(value="BrowsingHistory对象", description="浏览记录")
public class BrowsingHistory extends BasePojo {

    private static final long serialVersionUID = 1L;

    @Builder
    public BrowsingHistory(Long id,String dataState,Long createBy,LocalDateTime createTime,Long insuranceId,Integer sortNo){
        super(id, dataState);
        this.insuranceId=insuranceId;
        this.sortNo=sortNo;
        this.createTime=createTime;
        this.createBy=createBy;
    }

    @ApiModelProperty(value = "保险ID")
    private Long insuranceId;

    @ApiModelProperty(value = "排序")
    private Integer sortNo;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "创建人")
    private Long createBy;


}
