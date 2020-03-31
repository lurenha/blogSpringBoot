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
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *id,名称
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_tag")
public class Tag implements Serializable {
    @TableId(value = "ta_id",type = IdType.AUTO)
    private Long taId;
    @NotBlank(message = "Name不能为空")
    private String name;
    private Date createTime;
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist=false)
    private List<Blog> blogs;

    @TableField(exist=false)
    private Integer blogsNum;

}
