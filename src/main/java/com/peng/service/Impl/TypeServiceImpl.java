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

//    @Autowired
//    private TypeDao typeDao;
//
//    @Override
//    public Type findByid(Integer ty_id) {
//        return typeDao.findTypeByid(ty_id);
//    }
//
//    @Override
//    public List<Type> findall() {
//        return typeDao.findallType();
//    }
//
//
//    @MyCache
//    @Override
//    public List<Type> findallPro() {
//        List<Type> types = typeDao.findallTypePro();
//        return types;
//    }
//
//
//    @Override
//    public boolean addORedit(Type type) {
//        Integer ty_id = type.getTy_id();
//        if (ty_id != null) {//更新
//            typeDao.updateType(type);
//        } else {//添加
//            typeDao.addType(type);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteByid(Integer ty_id) {
//        Type type = typeDao.findTypeByidPro(ty_id);
//        if (type.getBlogs() == null || type.getBlogs().size() == 0) {
//            typeDao.deleteTypebyid(ty_id);
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}
