package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.MyUser;
import com.peng.entity.User;
import com.peng.mapper.UserMapper;
import com.peng.service.ISysRoleService;
import com.peng.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ISysRoleService sysRoleService;


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

    @Override
    public MyUser getMyUserById(Long usId) {
        MyUser res = new MyUser();
        final User user = userMapper.selectById(usId);
        BeanUtils.copyProperties(user, res);
        if (user == null) {
            return null;
        }
        res.setRole(sysRoleService.getById(user.getRoleId()));
        res.setPermissionList(this.getPermissionList(usId));
        return res;
    }

    @Override
    public User generateUserByGithubUsId(Long githubUsId, User saveUser) {
        final User one = this.getOne(new LambdaQueryWrapper<User>().eq(User::getOauthUsId, githubUsId));
        if (one != null) {
            one.setRole(sysRoleService.getById(one.getRoleId()));
            one.setPermissionList(this.getPermissionList(one.getUsId()));
            return one;
        } else {
            this.saveOrUpdate(saveUser);
            saveUser.setRole(sysRoleService.getById(saveUser.getRoleId()));
            saveUser.setPermissionList(this.getPermissionList(saveUser.getUsId()));
            return saveUser;
        }
    }

}
