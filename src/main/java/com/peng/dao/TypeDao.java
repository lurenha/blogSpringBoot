package com.peng.dao;

import com.peng.domain.Blog;
import com.peng.domain.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.List;

@Mapper
public interface TypeDao {
    //根据id查询
    @Select("select * from t_type where ty_id= #{ty_id}")
    Type findTypeByid(Integer ty_id);

    //根据id详细查询
    @Select("select * from t_type where ty_id= #{ty_id}")
    @Results(value = {
            @Result(id = true, property = "ty_id", column = "ty_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "blogs", column = "ty_id", many = @Many(select = "com.peng.dao.TypeDao.findBlogByType",
                    fetchType = FetchType.DEFAULT)),
    })
    Type findTypeByidPro(Integer ty_id);

    //根据id删除
    @Delete("delete from t_type where ty_id = #{ty_id}")
    int deleteTypebyid(Integer ty_id);

    //更新
    @Update("update t_type set name=#{name} where ty_id=#{ty_id};")
    int updateType(Type type);

    //增加
    @Insert("insert into t_type (name) values(#{name});")
    @Options(useGeneratedKeys = true, keyProperty = "ty_id", keyColumn = "ty_id")
    int addType(Type type);

    //查询所有
    @Select("select * from t_type")
    List<Type> findallType();

    //详细查询所有
    @Select("select * from t_type")
    @Results(value = {
            @Result(id = true, property = "ty_id", column = "ty_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "blogs", column = "ty_id", many = @Many(select = "com.peng.dao.TypeDao.findBlogByTypePublished",
                    fetchType = FetchType.DEFAULT)),
    })
    List<Type> findallTypePro();
    //----------------------------------------------------------------------------------------------------------------------
    @Select("select * from t_blog where published=true and ty_id=#{ty_id}")
    Blog findBlogByTypePublished(Integer ty_id);

    @Select("select * from t_blog where ty_id=#{ty_id}")
    Blog findBlogByType(Integer ty_id);
}

