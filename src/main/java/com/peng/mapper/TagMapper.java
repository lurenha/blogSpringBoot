package com.peng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.Blog;
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

    //详细查询所有
    @Results(value = {
            @Result(id = true, property = "taId", column = "ta_id"),
            @Result(property = "blogs", column = "ta_id", many = @Many(select = "com.peng.mapper.TagMapper.findBlogByTagPublished",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from t_tag")
    List<Tag> findTagPage();
    //----------------------------------------------------------------------------------------------------------------------
    @Select("select b.* from t_blog as b left join t_blog_tag as bt on bt.bl_id=b.bl_id where b.published=true and bt.ta_id=#{taId}")
    Blog findBlogByTagPublished(Long taId);
    //----------------------------------------------------------------------------------------------------------------------
    @Select("select count(*) from t_blog_tag where ta_id=#{taId}")
    Integer getBlogNumByTag(Long taId);
}
