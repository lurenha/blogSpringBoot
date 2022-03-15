package com.peng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.entity.Tag;
import com.peng.entity.Type;
import com.peng.entity.other.TimeLineBlog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    /***
     *  //查询首页Blog信息(关联标签，类型)
     */
    @Results(id = "blogInfo", value = {
            @Result(id = true, property = "blId", column = "bl_id"),
            @Result(property = "tags", column = "bl_id", many = @Many(select = "com.peng.mapper.BlogMapper.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "ty_id", one = @One(select = "com.peng.mapper.BlogMapper.findTypeByBlog",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("<script>" +
            "select bl_id,title,outline,background_image,recommend,commentabled,published,views,ty_id,create_time,update_time from t_blog where published=true" +
            "<if test='title!=null and title!=\"\"'>" +
            "AND title like concat('%',#{title},'%')" +
            "</if>" +
            "order by create_time desc" +
            "</script>"
    )
    List<Blog> findIndexPage(@Param("title") String title);

    /***
     *  //查询首页Blog信息(关联标签，类型)
     *  根据tag查找Blog(发布的)
     */
    @Select("select bl_id,title,outline,background_image,recommend,commentabled,published,views,ty_id,create_time,update_time from t_blog where ty_id = #{tyId} and published=true order by create_time desc")
    @ResultMap(value = "blogInfo")
    List<Blog> getPageByType(Long tyId);

    /***
     *  //查询首页Blog信息(关联标签，类型)
     *  根据tag查找Blog(发布的)
     */
    @Select("SELECT b.bl_id,b.title,b.outline,b.background_image,b.recommend,b.commentabled,b.published,b.views,b.ty_id,b.create_time,b.update_time FROM t_blog AS b LEFT JOIN t_blog_tag AS bt ON b.bl_id=bt.bl_id WHERE bt.ta_id=#{taId} and b.published=true order by b.create_time desc")
    @ResultMap(value = "blogInfo")
    List<Blog> getPageByTag(Long taId);


    /***
     *  //查询Blog完整信息(关联标签，类型，评论)
     */
    @Results(value = {
            @Result(id = true, property = "blId", column = "bl_id"),
            @Result(property = "tags", column = "bl_id", many = @Many(select = "com.peng.mapper.BlogMapper.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "ty_id", one = @One(select = "com.peng.mapper.BlogMapper.findTypeByBlog",
                    fetchType = FetchType.DEFAULT))
    })
    @Select("select * from t_blog where bl_id=#{blId} ")
    Blog findFullBlogById(Long blId);

    /***
     *  //查询Blog信息(关联标签ID)
     */
    @Results(value = {
            @Result(id = true, property = "blId", column = "bl_id"),
            @Result(property = "tagIds", column = "bl_id", one = @One(select = "com.peng.mapper.BlogMapper.findTagIdsById",
                    fetchType = FetchType.DEFAULT))
    })
    @Select("select * from t_blog where bl_id=#{blId} ")
    Blog findBlogWithTagIdsById(Long blId);

    /***
     *  //时间线搜索
     */
    @Results(value = {
            @Result(id = true, property = "blId", column = "bl_id"),
    })
    @Select("SELECT  DATE_FORMAT(create_time,'%c-%d') AS 'date',DATE_FORMAT(create_time,'%Y-%m') AS 'month',bl_id,title FROM t_blog WHERE published=TRUE GROUP BY bl_id,DATE_FORMAT(create_time,'%Y-%m') ORDER BY create_time desc")
    List<TimeLineBlog> findTimeLine();

    /***
     *  //删除blog_tag关联信息
     */
    @Delete("delete from t_blog_tag where bl_id = #{blId}")
    int deleteBlogTagBatch(@Param("blId") Long blId);

    /***
     *  //添加blog_tag关联信息
     */
    @Insert("<script>" +
            "INSERT INTO t_blog_tag(bl_id,ta_id) VALUES " +
            "<foreach collection='list' item='taId' separator=','>" +
            " (#{blId},#{taId})" +
            "</foreach>" +
            "</script>")
    int addBlogTagBatch(@Param("blId") Long blId, @Param("list") List<Long> list);

    /***
     *  //访问量+1
     */
    @Update("update t_blog set views=views+1,update_time=update_time where bl_id = #{blId}")
    int addViewsById(@Param("blId") Long blId);
    //---------------------------------------------------查询需要用到的子查询-------------------------------------------------------------

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

    /***
     *  //查询Blog关联TagId
     */
    @Select("select ta_id from t_blog_tag where bl_id=#{blId}")
    Long[] findTagIdsById(Long blId);
    //----------------------------------------------------------------------------------------------------------------------

    /***
     *  //根据博客查询对应的comments
     */
    @Results(id = "commentMap", value = {
            @Result(id = true, property = "coId", column = "co_id"),
            @Result(property = "parentId", column = "parent_id"),
    })
    @Select("select * from t_comment where bl_id=#{blId}")
    List<Comment> findCommentByBlog(Long blId);


//    /***
//     *  //查询评论的子评论
//     */
//    @ResultMap("commentMap")
//    @Select("select * from t_comment where parent_id=#{coId} order by create_time desc")
//    Comment findChildListByComment(Long coId);

//    /***
//     *  //查询评论的父评论
//     */
//    @Select("select * from t_comment where co_id=#{parentId}")
//    Comment findParentByComment(Long parentId);
//    //----------------------------------------------------------------------------------------------------------------------

}
