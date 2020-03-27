package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.User;

import java.util.List;

public interface IUserService extends IService<User> {
    User getAdminInfo();
    User verifyLogin(String username, String password);
    List<String> getPermissionList(Long usId);
}
