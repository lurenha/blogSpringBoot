package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.entity.User;
import com.peng.mapper.UserMapper;
import com.peng.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
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
