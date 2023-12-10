package org.yunyangu.yunyangu.mapper;

import org.apache.ibatis.annotations.*;
import org.yunyangu.yunyangu.entity.Group;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Select("select group_id from EnterGroup where user_id=#{userID}")
    List<Integer> getUserGroup(@Param("userID") int userID);

    @Select("select * from `Group` where group_id = ${groupID}")
    Group getGroupInfo(@Param("groupID") int groupID);

    @Insert("insert into `Group`(name,group_master,create_time) values(#{groupName},#{userID}), now())")
    int createGroup(int userID, String groupName, String master);

    @Insert("insert into EnterGroup(user_id,group_id) values(#{userID},#{groupID})")
    int joinGroup(int userID, int groupID);

    @Delete("delete from `Group` where group_id=#{groupID}")
    int deleteGroup(int groupID);

    @Delete("delete from EnterGroup where group_id=#{groupID}")
    void deleteEnterGroup(int groupID);

    @Delete("delete from EnterGroup where user_id=#{userID} and group_id=#{groupID}")
    void exitGroup(int userID, int groupID);
}
