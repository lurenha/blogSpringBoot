//package com.peng.dao;
//
//
//import com.peng.domain.Blog;
//import com.peng.domain.Tag;
//import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.mapping.FetchType;
//
//
//import java.util.List;
//
//@Mapper
//public interface TagDao {
//
//    //根据id查询
//    @Select("select * from t_tag where ta_id= #{ta_id}")
//    Tag findTagByid(Integer ta_id);
//
//    //根据id详细查询
//    @Select("select * from t_tag where ta_id= #{ta_id}")
//    @Results(value = {
//            @Result(id = true, property = "ta_id", column = "ta_id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "blogs", column = "ta_id", many = @Many(select = "com.peng.dao.TagDao.findBlogByTag",
//                    fetchType = FetchType.DEFAULT)),
//    })
//    Tag findTagByidPro(Integer ta_id);
//
//    //根据id删除
//    @Delete("delete from t_tag where ta_id = #{ta_id}")
//    int deleteTagbyid(Integer ta_id);
//
//    //更新
//    @Update("update t_tag set name=#{name} where ta_id=#{ta_id};" )
//    int updateTag(Tag tag);
//
//    //增加
//    @Insert("insert into t_tag (name) values(#{name});")
//    @Options(useGeneratedKeys = true,keyProperty = "ta_id",keyColumn = "ta_id")
//    int addTag(Tag tag);
//
//    //查询所有
//    @Select("select * from t_tag")
//    List<Tag> findallTag();
//
//    //详细查询所有
//    @Select("select * from t_tag")
//    @Results(value = {
//            @Result(id = true, property = "ta_id", column = "ta_id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "blogs", column = "ta_id", many = @Many(select = "com.peng.dao.TagDao.findBlogByTagPublished",
//                    fetchType = FetchType.DEFAULT)),
//    })
//    List<Tag> findallTagPro();
////----------------------------------------------------------------------------------------------------------------------
//    @Select("select b.* from t_blog as b left join t_blog_tag as bt on bt.bl_id=b.bl_id where b.published=true and bt.ta_id=#{ta_id}")
//    Blog findBlogByTagPublished(Integer ta_id);
//
//    @Select("select b.* from t_blog as b left join t_blog_tag as bt on bt.bl_id=b.bl_id where bt.ta_id=#{ta_id}")
//    Blog findBlogByTag(Integer ta_id);
//
//}
