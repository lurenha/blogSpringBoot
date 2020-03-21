package com.peng.entity.other;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {

    Integer pe_id;
    String menu_code;
    String menu_name;
    String permission_code;
    String permission_name;
    Boolean required_permission;

}
