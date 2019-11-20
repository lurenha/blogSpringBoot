package com.peng.dao;

import com.peng.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select * from t_user where us_id= #{us_id}")
    User findByid(Integer us_id);

    @Select("select * from t_user where username=#{name} and password=#{password}")
    User verifylogin(@Param("name")String name,@Param("password")String password);

    //更新
    @Update("update t_user set nickname=#{nickname},username=#{username},password=#{password},email=#{email},power=#{power},about=#{about},location=#{location},vx=#{vx},qq=#{qq},finaldate=#{finaldate},avatar=#{avatar} where us_id=#{us_id};")
    int updateUser(User user);

    //增加
    @Insert("insert into t_type (nickname,username,password,email,power,about,location,vx,qq,finaldate,avatar) values(#{nickname},#{username},#{password},#{email},#{power},#{about},#{location},#{vx},#{qq},#{finaldate},#{avatar});")
    @Options(useGeneratedKeys = true, keyProperty = "us_id", keyColumn = "us_id")
    int addUser(User user);
}
