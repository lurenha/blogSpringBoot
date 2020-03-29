package com.peng.controller.Admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.User;
import com.peng.entity.sys.SysRole;
import com.peng.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * 角色信息
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 获取角色列表
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "roleName", required = false) String roleName) {
        PageInfo<SysRole> rolePage = iSysRoleService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<SysRole>().like(Objects.nonNull(roleName), SysRole::getRoleName, roleName));
        return ResultUtil.success(rolePage, ResultCode.SUCCESS);
    }

    /**
     * 根据角色编号获取详细信息
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/{roleId}")
    public JsonResult getById(@PathVariable Long roleId) {
        SysRole sysRole = iSysRoleService.getById(roleId);
        return ResultUtil.success(sysRole, ResultCode.SUCCESS);
    }


    /**
     * 新增角色
     */
    //    @RequiresPermissions("user:find")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody SysRole role) {
        List<SysRole> roleList = iSysRoleService.list(new LambdaQueryWrapper<SysRole>().select(SysRole::getRoleName, SysRole::getRoleKey));
        for (SysRole sysRole : roleList) {
            if (sysRole.getRoleName().equals(role.getRoleName()) || sysRole.getRoleKey().equals(role.getRoleKey())) {
                return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
            }
        }
        Boolean bool = iSysRoleService.addRoleWithMenuBatch(role);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    /**
     * 修改保存角色
     */
    //    @RequiresPermissions("user:find")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody SysRole role) {
        if (Objects.isNull(role.getRoleId())) return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        List<SysRole> roleList = iSysRoleService.list(new LambdaQueryWrapper<SysRole>().select(SysRole::getRoleName, SysRole::getRoleKey).ne(SysRole::getRoleId, role.getRoleId()));
        for (SysRole sysRole : roleList) {
            if (sysRole.getRoleName().equals(role.getRoleName()) || sysRole.getRoleKey().equals(role.getRoleKey())) {
                return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
            }
        }
        Boolean bool = iSysRoleService.updateRoleWithMenuBatch(role);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 删除角色
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/delete/{roleIds}")
    public JsonResult remove(@PathVariable Long[] roleIds) {
        boolean bool = iSysRoleService.deleteByIdsWithMenuBatch(Arrays.asList(roleIds));
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}