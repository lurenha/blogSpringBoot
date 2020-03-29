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
    public JsonResult getById(@PathVariable Long roleId)
    {
        SysRole sysRole = iSysRoleService.getById(roleId);
        return ResultUtil.success(sysRole, ResultCode.SUCCESS);
    }

//
//    /**
//     * 新增角色
//     */
//    @PreAuthorize("@ss.hasPermi('system:role:add')")
//    @Log(title = "角色管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysRole role)
//    {
//        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
//        {
//            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
//        }
//        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
//        {
//            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
//        }
//        role.setCreateBy(SecurityUtils.getUsername());
//        return toAjax(roleService.insertRole(role));
//
//    }
//
//    /**
//     * 修改保存角色
//     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody SysRole role)
//    {
//        roleService.checkRoleAllowed(role);
//        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
//        {
//            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
//        }
//        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
//        {
//            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
//        }
//        role.setUpdateBy(SecurityUtils.getUsername());
//        return toAjax(roleService.updateRole(role));
//    }
//
//    /**
//     * 修改保存数据权限
//     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @PutMapping("/dataScope")
//    public AjaxResult dataScope(@RequestBody SysRole role)
//    {
//        roleService.checkRoleAllowed(role);
//        return toAjax(roleService.authDataScope(role));
//    }
//
//    /**
//     * 状态修改
//     */
//    @PreAuthorize("@ss.hasPermi('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @PutMapping("/changeStatus")
//    public AjaxResult changeStatus(@RequestBody SysRole role)
//    {
//        roleService.checkRoleAllowed(role);
//        role.setUpdateBy(SecurityUtils.getUsername());
//        return toAjax(roleService.updateRoleStatus(role));
//    }
//
//    /**
//     * 删除角色
//     */
//    @PreAuthorize("@ss.hasPermi('system:role:remove')")
//    @Log(title = "角色管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{roleIds}")
//    public AjaxResult remove(@PathVariable Long[] roleIds)
//    {
//        return toAjax(roleService.deleteRoleByIds(roleIds));
//    }
//
//    /**
//     * 获取角色选择框列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:role:query')")
//    @GetMapping("/optionselect")
//    public AjaxResult optionselect()
//    {
//        return AjaxResult.success(roleService.selectRoleAll());
//    }
}