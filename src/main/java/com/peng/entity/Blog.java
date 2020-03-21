package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.peng.domain.Comment;
import com.peng.domain.Tag;
import com.peng.domain.Type;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * id,标题，内容，简介，是否推荐，评论是否开启，是否发布，创建日期，最后修改日期，访问量，外键：类型id
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("t_blog")
public class Blog implements Serializable {
    @TableId(value = "bl_id",type = IdType.AUTO)
    private Long blId;
    private String title;
    private String content;
    private String outline;
    private Boolean recommend;
    private Boolean commentabled;
    private Boolean published;
    private Integer views;
    private Long tyId;
    private Date createTime;
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private List<Tag> tags;

    @TableField(exist=false)
    private List<Comment> comments;

    @TableField(exist=false)
    private Type type;

    @TableField(exist=false)
    private List<Integer> tag_ids;

}