//package com.peng.dao;
//
//import com.peng.domain.User;
//import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.mapping.FetchType;
//
//import java.util.List;
//
//@Mapper
//public interface UserDao {
//    @Results(value = {
//            @Result(id = true, property = "us_id", column = "us_id"),
//            @Result(property = "nickname", column = "nickname"),
//            @Result(property = "username", column = "username"),
//            @Result(property = "password", column = "password"),
//            @Result(property = "email", column = "email"),
//            @Result(property = "power", column = "power"),
//            @Result(property = "about", column = "about"),
//            @Result(property = "location", column = "location"),
//            @Result(property = "vx", column = "vx"),
//            @Result(property = "qq", column = "qq"),
//            @Result(property = "finaldate", column = "finaldate"),
//            @Result(property = "avatar", column = "avatar"),
//            @Result(property = "permissionlist", column = "role_id", many = @Many(select = "com.peng.dao.UserDao.findPermissions",
//                    fetchType = FetchType.DEFAULT)),
//    })
//    @Select("select * from t_user where us_id= #{us_id}")
////    User findByid(Integer us_id);
//
//    @Select("select * from t_user where username=#{name} and password=#{password}")
//    User verifylogin(@Param("name")String name,@Param("password")String password);
//
//    //更新
//    @Update("update t_user set nickname=#{nickname},username=#{username},password=#{password},email=#{email},power=#{power},about=#{about},location=#{location},vx=#{vx},qq=#{qq},finaldate=#{finaldate},avatar=#{avatar} where us_id=#{us_id};")
//    int updateUser(User user);
//
//    //增加
//    @Insert("insert into t_type (nickname,username,password,email,power,about,location,vx,qq,finaldate,avatar) values(#{nickname},#{username},#{password},#{email},#{power},#{about},#{location},#{vx},#{qq},#{finaldate},#{avatar});")
//    @Options(useGeneratedKeys = true, keyProperty = "us_id", keyColumn = "us_id")
//    int addUser(User user);
//    //------------------------------------------------------------------------------------------------------------------
//
//
//    /***
//     *  //根据User查对应的权限
//     */
//    @Select("SELECT p.`permission_code` FROM sys_permission p LEFT JOIN sys_role_permission rp ON p.`id`=rp.`permission_id` WHERE rp.`role_id`= #{role_id}")
//    List<String> findPermissions(Integer role_id);
//
//
//}
