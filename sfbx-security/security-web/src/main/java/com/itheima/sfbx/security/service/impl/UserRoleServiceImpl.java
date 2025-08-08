package com.itheima.sfbx.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.sfbx.security.mapper.UserRoleMapper;
import com.itheima.sfbx.security.pojo.UserRole;
import com.itheima.sfbx.security.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：用户角色关联表服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public boolean deleteUserRoleByUserId(Long userId) {
        UpdateWrapper<UserRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(UserRole::getUserId,userId);
        return remove(updateWrapper);
    }

    @Override
    public boolean deleteUserRoleInUserId(List<Long> userIds) {
        UpdateWrapper<UserRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(UserRole::getUserId,userIds);
        return remove(updateWrapper);
    }
}
