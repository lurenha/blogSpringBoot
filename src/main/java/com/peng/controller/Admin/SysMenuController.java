package com.peng.controller.Admin;

import com.peng.entity.MyUser;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.other.TreeSelect;
import com.peng.entity.sys.SysMenu;
import com.peng.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜单信息
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public JsonResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId, MyUser myUser) {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = iSysMenuService.selectMenuList(myUser.getUsId());
        Map<String, Object> map = new HashMap<>();
        map.put("checkedKeys", iSysMenuService.selectMenuListByRoleId(roleId));
        map.put("menus", iSysMenuService.buildMenuTreeSelect(menus));
        return ResultUtil.success(map, ResultCode.SUCCESS);
    }


    /**
     * 加载菜单列表树
     */
    @GetMapping(value = "/treeselect")
    public JsonResult roleMenuTreeSelect(MyUser myUser) {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = iSysMenuService.selectMenuList(myUser.getUsId());
        List<TreeSelect> treeSelects = iSysMenuService.buildMenuTreeSelect(menus);
        return ResultUtil.success(treeSelects, ResultCode.SUCCESS);
    }


}