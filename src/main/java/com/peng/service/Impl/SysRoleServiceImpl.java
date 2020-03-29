package com.peng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.sys.SysRole;
import com.peng.mapper.sys.SysRoleMapper;
import com.peng.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Override
    public PageInfo<SysRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<SysRole> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> list = this.list(queryWrapper);
        PageInfo<SysRole> result = new PageInfo<>(list);
        return result;
    }
}
