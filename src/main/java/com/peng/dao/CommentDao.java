package com.peng.dao;



import com.peng.domain.Comment;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface CommentDao {
    //查询
    @Select("select * from t_comment where co_id= #{co_id}")
    Comment findCommentByid(Integer co_id);

    //删除
    @Delete("delete from t_comment where co_id = #{co_id}")
    int deleteCommentByid(Integer co_id);

    //更新
    @Update("update t_comment set nickname=#{nickname},email=#{email},content=#{content},avatar=#{avatar},creatdate=#{creatdate},adminComment=#{adminComment},bl_id=#{bl_id},parent_id=#{parent_id},ip_id=#{ip_id} where co_id=#{co_id};" )
    int updateComment(Comment comment);

    //增加
    @Insert("insert into t_comment (nickname,email,content,avatar,creatdate,adminComment,bl_id,parent_id,ip_id) values(#{nickname},#{email},#{content},#{avatar},#{creatdate},#{adminComment},#{bl_id},#{parent_id},#{ip_id});")
    @Options(useGeneratedKeys = true,keyProperty = "co_id",keyColumn = "co_id")
    int addComment(Comment comment);

    //查询所有
    @Select("select * from t_comment")
    List<Comment> findAllComment();


    //------------------------------------------------------------------------------------------------------------------

}
