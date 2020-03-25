package com.peng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.entity.Tag;
import com.peng.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {

    @Results(id = "blogInfo", value = {
            @Result(property = "tags", column = "bl_id", many = @Many(select = "com.peng.mapper.BlogMapper.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "ty_id", one = @One(select = "com.peng.mapper.BlogMapper.findTypeByBlog",
                    fetchType = FetchType.DEFAULT)),
//            @Result(property = "comments", column = "bl_id", many = @Many(select = "com.peng.mapper.BlogMapper.findCommentByBlog",
//                    fetchType = FetchType.DEFAULT))
    })
    @Select("<script>" +
            "select bl_id,title,outline,background_image,recommend,commentabled,published,views,ty_id,create_time,update_time from t_blog where published=true" +
            "<if test='title!=null and title!=\"\"'>" +
            "AND title like \"%${title}%\"" +
            "</if>" +
            "order by create_time desc" +
            "</script>"
    )
    List<Blog> findIndexPage(@Param("title") String title);


    /***
     *  //根据博客查询对应的tags（内联查询）
     */
    @Select("SELECT * FROM t_tag WHERE t_tag.`ta_id` IN (SELECT bt.`ta_id` FROM t_blog_tag AS bt WHERE bt.`bl_id`=#{blId})")
    List<Tag> findTagsByBlog(Long blId);


    /***
     *  //根据博客查询对应的type
     */
    @Select("select * from t_type where ty_id= #{tyId}")
    Type findTypeByBlog(Long tyId);
    //----------------------------------------------------------------------------------------------------------------------
    /***
     *  //根据博客查询对应的comments
     */
    @Results(id = "commentMap", value = {
            @Result(property = "childList", column = "co_id", many = @Many(select = "com.peng.mapper.BlogMapper.findChildListByComment",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "parent", column = "parent_id", one = @One(select = "com.peng.mapper.BlogMapper.findParentByComment",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from t_comment where bl_id=#{blId} and parent_id is null order by create_time desc")
    List<Comment> findCommentByBlog(Long blId);

    /***
     *  //查询评论的子评论
     */
    @ResultMap("commentMap")
    @Select("select * from t_comment where parent_id=#{coId} order by create_time desc")
    Comment findChildListByComment(Long coId);

    /***
     *  //查询评论的父评论
     */
    @Select("select * from t_comment where co_id=#{parentId}")
    Comment findParentByComment(Long parentId);
    //----------------------------------------------------------------------------------------------------------------------
}
