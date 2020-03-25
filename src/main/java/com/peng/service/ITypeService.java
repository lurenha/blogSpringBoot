package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.Tag;
import com.peng.entity.Type;

import java.util.List;

public interface ITypeService extends IService<Type> {
    List<Type> getIndexTypes();
//    Type findByid(Integer ty_id);
//    List<Type> findall();
//    List<Type> findallPro();
//    boolean addORedit(Type type);
//    boolean deleteByid(Integer ty_id);
}
