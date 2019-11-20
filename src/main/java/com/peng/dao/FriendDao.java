package com.peng.dao;

import com.peng.domain.Friend;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendDao {
    //查询
    @Select("select * from t_friend where fr_id= #{fr_id}")
    Friend findFriendByid(Integer fr_id);

    //删除
    @Delete("delete from t_friend where fr_id = #{fr_id}")
    int deleteFriendByid(Integer fr_id);

    //更新
    @Update("update t_friend set nickname=#{nickname},description=#{description},website=#{website},flag=#{flag} where fr_id=#{fr_id};" )
    int updateFriend(Friend Friend);

    //增加
    @Insert("insert into t_friend (nickname,flag,description,website) values(#{nickname},#{flag},#{description},#{website});")
    @Options(useGeneratedKeys = true,keyProperty = "fr_id",keyColumn = "fr_id")
    int addFriend(Friend Friend);

    //查询所有
    @Select("select * from t_friend")
    List<Friend> findAllFriend();
}
