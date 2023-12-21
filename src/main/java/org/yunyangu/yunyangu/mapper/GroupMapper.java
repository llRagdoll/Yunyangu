package org.yunyangu.yunyangu.mapper;

import org.apache.ibatis.annotations.*;
import org.yunyangu.yunyangu.entity.Group;

import java.util.List;
import static java.time.LocalTime.now;

@Mapper
public interface GroupMapper {
    @Select("select group_id from EnterGroup where user_id=#{userID}")
    List<Integer> getUserGroup(@Param("userID") int userID);

    @Select("select * from `Group` where group_id = ${groupID}")
    Group getGroupInfo(@Param("groupID") Integer groupID);

    @Insert("INSERT INTO `Group`(name, group_master, create_time) VALUES(#{groupName}, #{userID}, now())")
    void createGroup(int userID, String groupName, String master);

    @Insert("insert into EnterGroup(user_id,group_id) values(#{userID},#{groupID})")
    int joinGroup(Integer userID, Integer groupID);
    //获得刚刚插入的群组的id
    @Select("SELECT LAST_INSERT_ID()")
    Integer getLastInsertedGroupId();

    @Delete("delete from `Group` where group_id=#{groupID}")
    int deleteGroup(int groupID);

    @Delete("delete from EnterGroup where group_id=#{groupID}")
    void deleteEnterGroup(int groupID);


    @Delete("delete from EnterGroup where user_id=#{userID} and group_id=#{groupID}")
    void exitGroup(int userID, int groupID);

    //获得该群的成员
   @Select("select user_id from EnterGroup where group_id=#{groupID}")
    List<Integer> getGroupUser(int groupID);

    @Select("select group_id from `Group` where name=#{groupName}")
    Integer getGroupByName(String groupName);


}
