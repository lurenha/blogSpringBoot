package com.peng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.other.RouterVo;
import com.peng.entity.sys.SysMenu;

import java.util.List;

public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
     List<RouterVo> buildMenus(List<SysMenu> menus);
}
