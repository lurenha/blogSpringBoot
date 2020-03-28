package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *id,昵称，用户名，密码，邮箱，权限，简介，所在地，微信，扣扣
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("sys_user")
public class User implements Serializable {
    @TableId(value = "us_id",type = IdType.AUTO)
    private Long usId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String about;
    private String location;
    private String wechat;
    private String qq;
    private String avatar;
    private Long roleId;
    private Date createTime;
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private List<String> permissionList;

    @TableField(exist=false)
    private List<String> roles;




}
