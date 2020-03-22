package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.User;

public interface IUserService extends IService<User> {
    User verifyLogin(String account, String password);
//    User findByid(Integer us_id);
//    boolean addORedit(User user);
}
