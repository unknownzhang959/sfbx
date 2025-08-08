package com.itheima.sfbx.security.hystrix;

import com.itheima.sfbx.framework.commons.dto.security.AuthChannelVO;
import com.itheima.sfbx.security.feign.AuthChannelFeign;
import com.itheima.sfbx.security.feign.CompanyFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName AuthChannelFeign.java
 * @Description TODO
 */
public class AuthChannelHystrix implements AuthChannelFeign {


    @Override
    public AuthChannelVO findAuthChannelByCompanyNoAndChannelLabel(String companyNo, String channelLabel) {
        return null;
    }
}
