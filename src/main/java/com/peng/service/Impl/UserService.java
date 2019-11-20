package com.peng.service.Impl;

import com.peng.dao.UserDao;
import com.peng.domain.User;
import com.peng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IUserService")
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByid(Integer us_id) {
        return userDao.findByid(us_id);
    }

    @Override
    public User verifylogin(String name, String password) {
        return userDao.verifylogin(name, password);
    }
}
