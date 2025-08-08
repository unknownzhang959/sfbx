package com.itheima.sfbx.security.hystrix;

import com.itheima.sfbx.framework.commons.dto.security.*;
import com.itheima.sfbx.security.feign.UserFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserHtstrix.java
 * @Description UserFeign的Htstrix
 */
@Component
public class UserHtstrix implements UserFeign {


    @Override
    public UserVO usernameLogin(String username, String company) {
        return null;
    }

    @Override
    public UserVO mobileLogin(String mobile, String company) {
        return null;
    }

    @Override
    public UserVO wechatLogin(String openId, String company) {
        return null;
    }

    @Override
    public List<RoleVO> findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ResourceVO> findResourceByUserId(Long userId) {
        return null;
    }

    @Override
    public DataSecurityVO userDataSecurity(QueryDataSecurityVO queryDataSecurityVO) {
        return null;
    }

    @Override
    public UserVO findUserById(Long userId) {
        return null;
    }


}
