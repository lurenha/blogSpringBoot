package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * id,标题，内容，简介，是否推荐，评论是否开启，是否发布，创建日期，最后修改日期，访问量，外键：类型id
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_blog")
public class Blog implements Serializable {
    @TableId(value = "bl_id",type = IdType.AUTO)
    private Long blId;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;
    @NotBlank(message = "简介不能为空")
    private String outline;
    private String backgroundImage;
    private Boolean recommend;
    private Boolean commentabled;
    private Boolean published;
    private Integer views;
    private Long tyId;
    private Date createTime;
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private Integer commentNum;

    @TableField(exist=false)
    private List<Tag> tags;

    @TableField(exist=false)
    private List<Comment> comments;

    @TableField(exist=false)
    private Type type;

    @TableField(exist=false)
    private Long[] tagIds;

}
