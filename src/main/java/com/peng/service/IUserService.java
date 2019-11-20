package com.peng.service;


import com.peng.domain.User;

public interface IUserService {
    User findByid(Integer us_id);
    User verifylogin(String name,String password);
}
