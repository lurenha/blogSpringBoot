package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.peng.entity.sys.SysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *id,昵称，用户名，密码，邮箱，权限，简介，所在地，微信，扣扣
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@TableName("sys_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @TableId(value = "us_id",type = IdType.AUTO)
    private Long usId;
    @NotBlank(message = "用户昵称不能为空")
    @Size(min = 0, max = 10, message = "用户昵称长度不能超过10个字符")
    private String name;
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 20, message = "用户账号长度不能超过20个字符")
    private String username;
    private String password;
    private String email;
    private String about;
    private String location;
    private String wechat;
    private String qq;
    private String avatar;
    @NotNull(message = "用户角色ID不能为空")
    private Long roleId;
    private Date createTime;
    private Date updateTime;
    private Integer oauthType;
    private Long oauthUsId;
    private String oauthToken;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private List<String> permissionList;

    @TableField(exist=false)
    private SysRole role;




}
