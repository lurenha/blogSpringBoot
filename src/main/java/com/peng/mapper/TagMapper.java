package com.peng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.Tag;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    @Results(value = {
            @Result(id = true, property = "taId", column = "ta_id"),
            @Result(property = "blogsNum", column = "ta_id", many = @Many(select = "com.peng.mapper.TagMapper.getBlogNumByTag",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from t_tag")
    List<Tag> getIndexTag();
    //----------------------------------------------------------------------------------------------------------------------
    @Select("select count(*) from t_blog_tag where ta_id=#{taId}")
    Integer getBlogNumByTag(Long taId);
}
