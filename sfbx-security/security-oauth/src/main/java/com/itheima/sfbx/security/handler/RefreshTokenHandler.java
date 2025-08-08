package com.itheima.sfbx.security.handler;

import com.itheima.sfbx.framework.commons.dto.security.UserVO;

import java.security.Principal;
import java.util.Map;

/**
 * @ClassName RefreshTokenHandler.java
 * @Description 刷新令牌处理
 */
public interface RefreshTokenHandler {

    /***
     * @description 刷新令牌
     * @return
     */
    public UserVO refreshToken(Principal principal, Map<String, String> parameters);
}
