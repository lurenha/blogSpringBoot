package com.peng.dao;

import com.peng.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface BlogDao {
    @Results(value = {
            @Result(id = true, property = "bl_id", column = "bl_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "outline", column = "outline"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "recommend", column = "recommend"),
            @Result(property = "commentabled", column = "commentabled"),
            @Result(property = "published", column = "published"),
            @Result(property = "creatdate", column = "creatdate"),
            @Result(property = "finaldate", column = "finaldate"),
            @Result(property = "views", column = "views"),
            @Result(property = "ty_id", column = "ty_id"),
            @Result(property = "tags", column = "bl_id", many = @Many(select = "com.peng.dao.BlogDao.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from t_blog where bl_id= #{bl_id}")
    Blog findByid(Integer bl_id);

    @Results(id = "blogMap", value = {
            @Result(id = true, property = "bl_id", column = "bl_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "outline", column = "outline"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "recommend", column = "recommend"),
            @Result(property = "commentabled", column = "commentabled"),
            @Result(property = "published", column = "published"),
            @Result(property = "creatdate", column = "creatdate"),
            @Result(property = "finaldate", column = "finaldate"),
            @Result(property = "views", column = "views"),
            @Result(property = "ty_id", column = "ty_id"),
            @Result(property = "tags", column = "bl_id", many = @Many(select = "com.peng.dao.BlogDao.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "comments", column = "bl_id", many = @Many(select = "com.peng.dao.BlogDao.findCommentByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "ty_id", one = @One(select = "com.peng.dao.BlogDao.findTypeByBlog",
                    fetchType = FetchType.DEFAULT))
    })
    @Select("select * from t_blog where bl_id= #{bl_id}")
    Blog findByidPro(Integer bl_id);

    //根据条件查找(简略信息)
    @Results(id = "blogSimplicity", value = {
            @Result(id = true, property = "bl_id", column = "bl_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "outline", column = "outline"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "recommend", column = "recommend"),
            @Result(property = "commentabled", column = "commentabled"),
            @Result(property = "published", column = "published"),
            @Result(property = "creatdate", column = "creatdate"),
            @Result(property = "finaldate", column = "finaldate"),
            @Result(property = "views", column = "views"),
            @Result(property = "ty_id", column = "ty_id"),
            @Result(property = "tags", column = "bl_id", many = @Many(select = "com.peng.dao.BlogDao.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "ty_id", one = @One(select = "com.peng.dao.BlogDao.findTypeByBlog",
                    fetchType = FetchType.DEFAULT))
    })
    @Select("<script>" +
            "select bl_id,title,outline,flag,recommend,commentabled,published,creatdate,finaldate,ty_id,views from t_blog " +
            "where 1=1" +
            "<when test='title!=null'>" +
            "AND title like #{title}" +
            "</when>" +
            "<when test='ty_id!=null'>" +
            "AND ty_id = #{ty_id}" +
            "</when>" +
            "order by finaldate desc"+
            "</script>")
    List<Blog> findallBlog(@Param("title") String title, @Param("ty_id") Integer ty_id);

    //查找所有发布的blog
    @Select("<script>" +
            "select bl_id,title,outline,flag,recommend,commentabled,published,creatdate,finaldate,ty_id,views from t_blog where published=true"+
            "<when test='title!=null'>" +
            "AND title like #{title}" +
            "</when>" +
            "order by finaldate desc"+
            "</script>"
    )
    @ResultMap(value = "blogSimplicity")
    List<Blog> findallPubBlog(@Param("title") String title);


    //根据type查找Blog(发布的)
    @Select("select * from t_blog where ty_id = #{ty_id} and published=true order by creatdate desc")
    @ResultMap(value = "blogSimplicity")
    List<Blog> findsBlogbyType(Integer ty_id);

    //根据tag查找Blog(发布的)
    @Select("SELECT * FROM t_blog AS b LEFT JOIN t_blog_tag AS bt ON b.bl_id=bt.bl_id WHERE bt.ta_id=#{ta_id} and b.published=true order by b.creatdate desc")
    @ResultMap(value = "blogSimplicity")
    List<Blog> findsBlogbyTag(Integer ta_id);


    //删除
    @Delete("delete from t_blog where bl_id = #{bl_id}")
    int deleteBlogbyid(Integer bl_id);

    //更新
    @Update("update t_blog set title=#{title},content=#{content},outline=#{outline},flag=#{flag},recommend=#{recommend},commentabled=#{commentabled},published=#{published},finaldate=#{finaldate}, ty_id=#{ty_id} where bl_id=#{bl_id};")
    int updateBlog(Blog blog);


    //增加
    @Insert("insert into t_blog (title,content,outline,flag,recommend,commentabled,published,finaldate,creatdate,views,ty_id) values(#{title},#{content},#{outline},#{flag},#{recommend},#{commentabled},#{published},#{finaldate},#{creatdate},#{views},#{ty_id});")
    @Options(useGeneratedKeys = true, keyProperty = "bl_id", keyColumn = "bl_id")
    int addBlog(Blog blog);

    //增加关联标签
    @Insert("<script> insert into t_blog_tag (bl_id,ta_id) values <foreach collection='tags' item='item' separator=',' > (#{bl_id},#{item.ta_id}) </foreach> </script>")
    int addBlog_tags(Blog blog);

    //删除关联标签
    @Delete("delete from t_blog_tag where bl_id = #{bl_id};")
    int deleteBlog_tags(Integer bl_id);

    //删除关联评论
    @Delete("delete from t_comment where bl_id = #{bl_id};")
    int deleteBlog_comments(Integer bl_id);

    //设置发布状态
    @Update("update t_blog set published=#{published} where bl_id = #{bl_id} ")
    int setPublishedByid(@Param("bl_id") Integer bl_id, @Param("published") Boolean published);

    //时间线搜索
    @Select("SELECT  DATE_FORMAT(creatdate,'%c-%d') AS 'date',DATE_FORMAT(creatdate,'%Y-%m') AS 'month',bl_id,title FROM t_blog WHERE published=TRUE GROUP BY bl_id,DATE_FORMAT(creatdate,'%Y-%m') ORDER BY creatdate desc")
    List<TimeLineBlog> findtimeLine();

    //访问量增加
    @Update("UPDATE t_blog SET views=views+1 WHERE bl_id=#{bl_id}")
    int addViews(Integer bl_id);

    //----------------------------------------------------------------------------------------------------------------------------------------

    /***
     *  //根据博客查询对应的type
     */
    @Select("select * from t_type where ty_id= #{ty_id}")
    Type findTypeByBlog(Integer ty_id);

    /***
     *  //根据博客查询对应的tags（内联查询）
     */
    @Select("SELECT * FROM t_tag WHERE t_tag.`ta_id` IN (SELECT bt.`ta_id` FROM t_blog_tag AS bt WHERE bt.`bl_id`=#{bl_id})")
    List<Tag> findTagsByBlog(Integer bl_id);

    /***
     *  //根据博客查询对应的comments
     */
    @Results(id = "commentMap", value = {
            @Result(id = true, property = "co_id", column = "co_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "email", column = "email"),
            @Result(property = "content", column = "content"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "creatdate", column = "creatdate"),
            @Result(property = "adminComment", column = "adminComment"),
            @Result(property = "parent_id", column = "parent_id"),
            @Result(property = "childcoments", column = "co_id", many = @Many(select = "com.peng.dao.BlogDao.findChildsByComment",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "ipAddress", column = "ip_id", one = @One(select = "com.peng.dao.BlogDao.findIPByComment",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "parent", column = "parent_id", one = @One(select = "com.peng.dao.BlogDao.findParentByComment",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from t_comment where bl_id=#{bl_id} and parent_id is null order by creatdate desc")
    List<Comment> findCommentByBlog(Integer bl_id);
    //------------------------------------------------------------------------------------------------------------------

    /***
     *  //根据评论查询对应的childcoments
     */
    @ResultMap("commentMap")
    @Select("select * from t_comment where parent_id=#{co_id} order by creatdate desc")
    Comment findChildsByComment(Integer co_id);

    /***
     *  //根据评论查询对应的ipAddress
     */
    @Select("select * from t_ip where ip_id= #{ip_id}")
    IPAddress findIPByComment(Integer ip_id);

    /***
     *  //根据评论查询对应的parent
     */
    @Select("select * from t_comment where co_id=#{parent_id}")
    Comment findParentByComment(Integer parent_id);
    //------------------------------------------------------------------------------------------------------------------

}
