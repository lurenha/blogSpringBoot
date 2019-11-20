package com.peng.dao;

import com.peng.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from t_user where us_id= #{us_id}")
    User findByid(Integer us_id);

    @Select("select * from t_user where username=#{name} and password=#{password}")
    User verifylogin(@Param("name")String name,@Param("password")String password);
}
