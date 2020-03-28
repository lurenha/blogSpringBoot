package com.peng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT p.`permission_code` FROM sys_permission p LEFT JOIN sys_role_permission rp ON p.`id`=rp.`permission_id` LEFT JOIN sys_user us on us.`role_id`=rp.`role_id` WHERE us.`us_id`=#{usId}")
    List<String> getPermissionsById(Long usId);

    @Select("SELECT role_key FROM sys_role ro LEFT JOIN sys_user us on us.role_id=ro.role_id WHERE us.us_id=#{usId}")
    String getRoleKeyById(Long usId);
}
