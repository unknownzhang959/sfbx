package com.itheima.sfbx.security.hystrix;

import com.itheima.sfbx.framework.commons.dto.security.CompanyVO;
import com.itheima.sfbx.framework.commons.dto.security.UserVO;
import com.itheima.sfbx.security.feign.CompanyFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName CompanyFeign.java
 * @Description CompanyFeign的Htstrix
 */
@Component
public class CompanyHtstrix implements CompanyFeign {


    @Override
    public CompanyVO findCompanyByNo(String companyNo) {
        return null;
    }

    @Override
    public List<CompanyVO> findCompanyByNos(List<String> companyNos) {
        return null;
    }
}
