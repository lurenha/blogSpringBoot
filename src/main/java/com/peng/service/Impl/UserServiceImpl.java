package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.aspect.MyCache;
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
    public User verifyLogin(String account, String password) {
        return this.getOne(new LambdaQueryWrapper<User>().eq(User::getAccount,account).eq(User::getPassword,password));
    }

    @Override
    @MyCache
    public List<String> getPermissionList(Long usId) {
        return userMapper.getPermissionsById(usId);
    }
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public User findByid(Integer us_id) {
//        return userDao.findByid(us_id);
//    }
//
//    @Override
//    public User verifylogin(String name, String password) {
//        return userDao.verifylogin(name, password);
//    }
//
//    @Override
//    public boolean addORedit(User user) {
//        Integer us_id = user.getUs_id();
//        if (us_id != null) {//更新
//            userDao.updateUser(user);
//        } else {//添加
//            userDao.addUser(user);
//        }
//        return true;
//    }
}
