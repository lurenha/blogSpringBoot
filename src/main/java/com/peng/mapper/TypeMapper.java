package com.peng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {
    @Results(value = {
            @Result(id = true, property = "tyId", column = "ty_id"),
            @Result(property = "blogsNum", column = "ty_id", many = @Many(select = "com.peng.mapper.TypeMapper.getBlogNumByType",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from t_type")
    List<Type> getIndexTypes();
//----------------------------------------------------------------------------------------------------------------------
    @Select("select count(*) from t_blog where ty_id=#{tyId}")
    Integer getBlogNumByType(Long tyId);
}
