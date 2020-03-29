package com.peng.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.peng.entity.sys.SysRole;

import java.util.List;


public interface ISysRoleService extends IService<SysRole> {
    PageInfo<SysRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<SysRole> queryWrapper);

    Boolean addRoleWithMenuBatch(SysRole sysRole);

    Boolean updateRoleWithMenuBatch(SysRole sysRole);

    Boolean deleteByIdsWithMenuBatch(List<Long> roleIds);
}
