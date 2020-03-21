package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.peng.domain.Blog;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *id,名称
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("t_type")
public class Type implements Serializable {
    @TableId(value = "ty_id",type = IdType.AUTO)
    private Long tyId;
    private String name;
    private Date createTime;
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private List<Blog> blogs;

}
