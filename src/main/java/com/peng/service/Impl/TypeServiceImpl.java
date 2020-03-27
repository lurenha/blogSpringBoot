package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.aspect.MyCache;
import com.peng.entity.Tag;
import com.peng.entity.Type;
import com.peng.mapper.TagMapper;
import com.peng.mapper.TypeMapper;
import com.peng.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getIndexTypes() {
        return typeMapper.getIndexTypes();
    }

}
