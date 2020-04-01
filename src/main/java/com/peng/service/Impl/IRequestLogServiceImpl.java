package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.RequestLog;
import com.peng.mapper.RequestLogMapper;
import com.peng.service.IRequestLogService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IRequestLogServiceImpl extends ServiceImpl<RequestLogMapper, RequestLog> implements IRequestLogService {


    @Override
    public PageInfo<RequestLog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<RequestLog> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<RequestLog> list = this.list(queryWrapper);
        PageInfo<RequestLog> result = new PageInfo<>(list);
        return result;
    }
}
