package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.config.exception.CaptchaExpireException;
import com.peng.entity.MyUser;
import com.peng.entity.User;
import com.peng.mapper.UserMapper;
import com.peng.service.IUserService;
import com.peng.util.Constants;
import com.peng.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getAdminInfo() {
        User admin = this.getOne(new LambdaQueryWrapper<User>().eq(User::getRoleId, 1));//roleId自行修改
        return admin;
    }

    @Override
    public User verifyLogin(String username, String password) {
        final User res = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getPassword, password));
        if (res != null) {
            res.setPermissionList(this.getPermissionList(res.getUsId()));
        }
        return res;
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

    @Override
    public PageInfo<User> getListByPage(Integer pageNum, Integer pageSize, Wrapper<User> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = this.list(queryWrapper);
        PageInfo<User> result = new PageInfo<>(list);
        return result;
    }

}
