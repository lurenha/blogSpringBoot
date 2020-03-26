package com.peng.entity.other;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
public class TimeLineBlog implements Serializable {
    private Long blId;
    private String title;
    private String date;
    private String month;

}
