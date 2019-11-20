package com.peng.service;

import com.peng.domain.Type;

import java.util.List;

public interface ITypeService {
    Type findByid(Integer ty_id);
    List<Type> findall();
    List<Type> findallPro();
    boolean addORedit(Type type);
    boolean deleteByid(Integer ty_id);
}
