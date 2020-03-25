package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * id,昵称，邮箱，内容，头像，创建时间，外键：博客id 父节点id
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("t_comment")
public class Comment implements Serializable {
    @TableId(value = "co_id", type = IdType.AUTO)
    private Long coId;
    private String name;
    private String email;
    private String content;
    private Boolean isAdmin;
    private Long blId;
    private Long parentId;
    private Long ipId;
    private Date createTime;
    private Date updateTime;

    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist = false)
    private Blog blog;

    @TableField(exist = false)
    private List<Comment> childList;

    @TableField(exist = false)
    private Comment parent;


}
