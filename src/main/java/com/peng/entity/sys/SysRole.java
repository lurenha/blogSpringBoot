package com.peng.entity.sys;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表 sys_role
 *
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
@Data
@TableName("sys_role")
public class SysRole implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色排序 */
    private String roleSort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限） */
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private Date createTime;

    private Date updateTime;

//---------------------------------------------------------------------------------------------------------------------
    /** 用户是否存在此角色标识 默认不存在 */
    @TableField(exist=false)
    private boolean flag = false;

    /** 菜单组 */
    @TableField(exist=false)
    private Long[] menuIds;


}
