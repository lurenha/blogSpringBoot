package com.peng.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("t_ip")
public class IPAddress implements Serializable {
    @TableId(value = "ip_id",type = IdType.AUTO)
    private Long ipId;
    private String ipCode;
    private String address;
    private Date createTime;
    private Date updateTime;
}
