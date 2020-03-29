package com.peng.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.entity.sys.SysRole;
import com.peng.mapper.sys.SysRoleMapper;
import com.peng.service.ISysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
}
