package org.yunyangu.yunyangu.service;

import org.yunyangu.yunyangu.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getUserGroup(int userID);

    Group getGroupInfo(Integer groupID);

    int createGroup(Integer userID, String groupName);

    int joinGroup(Integer userID, Integer groupID);

    int deleteGroup(int groupID);

    void exitGroup(int userID, int groupID);

    Integer getGroupByName(String groupName);

    List<Integer> getGroupMember(Integer groupId);
}
