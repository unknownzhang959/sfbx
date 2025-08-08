package com.itheima.sfbx.security.handler;

import com.itheima.sfbx.framework.commons.dto.security.UserVO;

/**
 * @ClassName LogoutHandler.java
 * @Description 退出处理器
 */
public interface LogoutHandler {

    /***
     * @description 退出
     * @param userVO 退出用户
     * @return
     */
    Boolean logout(UserVO userVO);
}
