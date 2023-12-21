package org.yunyangu.yunyangu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunyangu.yunyangu.entity.Group;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.mapper.GroupMapper;
import org.yunyangu.yunyangu.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public int createGroup(Integer userID, String groupName) {
        groupMapper.createGroup(userID, groupName, "master");
        int groupId=groupMapper.getLastInsertedGroupId();
        groupMapper.joinGroup(userID, groupId);
        return groupId;
    }
    @Override
    public List<Group> getUserGroup(int userID) {
        List<Integer> groupIds=groupMapper.getUserGroup(userID);
        List<Group> groups = new ArrayList<>();
        for (Integer groupId : groupIds) {
            Group group = groupMapper.getGroupInfo(groupId);
            if (group != null) {
                groups.add(group);
            }
        }
       return groups;
    }

    @Override
    public Group getGroupInfo(Integer groupID) {
        return groupMapper.getGroupInfo(groupID);
    }

    @Override
    public int joinGroup(Integer userID, Integer groupID) {
        return groupMapper.joinGroup(userID, groupID);
    }

    @Override
    public int deleteGroup(int groupID) {
        groupMapper.deleteEnterGroup(groupID);
       return  groupMapper.deleteGroup(groupID);
    }

    @Override
    public Integer getGroupByName(String groupName) {
        System.out.println("groupName:"+groupName);
        return groupMapper.getGroupByName(groupName);
    }

    @Override
    public void exitGroup(int userID, int groupID) {
        groupMapper.exitGroup(userID, groupID);
    }

    //获取该群成员
    @Override
    public List<Integer> getGroupMember(Integer groupId){
        return groupMapper.getGroupUser(groupId);
    }

}
