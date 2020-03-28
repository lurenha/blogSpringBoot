package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.entity.User;
import com.peng.mapper.UserMapper;
import com.peng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getAdminInfo() {
        User admin = this.getOne(new LambdaQueryWrapper<User>().eq(User::getRoleId, 1));//roleId自行修改
        return admin;
    }

    @Override
    public User verifyLogin(String username, String password) {
        return this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username).eq(User::getPassword,password));
    }

    @Override
    public List<String> getPermissionList(Long usId) {
        if (this.isAdmin(usId)) {
            return userMapper.getPermissionsAll();
        } else {
            return userMapper.getPermissionsById(usId);
        }

    }

    @Override
    public boolean isAdmin(Long usId) {
        return userMapper.getRoleKeyById(usId).equals("admin");
    }

}
