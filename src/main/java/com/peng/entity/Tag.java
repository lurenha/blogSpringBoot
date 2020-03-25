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
 *id,名称
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("t_tag")
public class Tag implements Serializable {
    @TableId(value = "ta_id",type = IdType.AUTO)
    private Long taId;
    private String name;
    private Date createTime;
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private List<Blog> blogs;

    @TableField(exist=false)
    private Integer blogsNum;

}
