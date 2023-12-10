package org.yunyangu.yunyangu.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select friend_id from Friend where user_id=#{userID}")
    List<Integer> getFriend(@Param("userID") Integer userID);

    @Insert("insert into friend(user_id,friend_id) values(#{userID},#{friendID})")
    Integer addFriend(@Param("userID") int userID,@Param("friendID") int friendID);

    @Delete("delete from friend where user_id=#{userID} and friend_id=#{friendID}")
    Integer deleteFriend(@Param("userID") int userID,@Param("friendID") int friendID);
}
