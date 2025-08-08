package com.itheima.sfbx.instance.hystrix;

import com.itheima.sfbx.framework.commons.dto.analysis.AnalysisCustomerInsuranceDTO;
import com.itheima.sfbx.framework.commons.dto.analysis.AnalysisCustomerSexDTO;
import com.itheima.sfbx.framework.commons.dto.analysis.AnalysisInsuranceTypeDTO;
import com.itheima.sfbx.framework.commons.dto.log.LogBusinessVO;
import com.itheima.sfbx.instance.feign.AnalysisBusinessFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AnalysisBusinessHystrix
 *
 * @author: wgl
 * @describe: 业务部分统计分析
 * @date: 2022/12/28 10:10
 */
@Component
public class AnalysisBusinessHystrix implements AnalysisBusinessFeign {


    @Override
    public AnalysisCustomerInsuranceDTO countCustomerInstance() {
        return null;
    }

    @Override
    public AnalysisCustomerSexDTO countWarrantySex() {
        return null;
    }

    @Override
    public List<AnalysisInsuranceTypeDTO> countCustomerInstanceType() {
        return null;
    }
}
