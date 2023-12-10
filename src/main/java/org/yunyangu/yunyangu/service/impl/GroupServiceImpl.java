package org.yunyangu.yunyangu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunyangu.yunyangu.mapper.GroupMapper;
import org.yunyangu.yunyangu.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public int createGroup(int userID, String groupName) {
        return groupMapper.createGroup(userID, groupName, "master");
    }
    @Override
    public List<Integer> getUserGroup(int userID) {
        return groupMapper.getUserGroup(userID);
    }

    @Override
    public Object getGroupInfo(int groupID) {
        return groupMapper.getGroupInfo(groupID);
    }

    @Override
    public int joinGroup(int userID, int groupID) {
        return groupMapper.joinGroup(userID, groupID);
    }

    @Override
    public int deleteGroup(int groupID) {
        groupMapper.deleteEnterGroup(groupID);
       return  groupMapper.deleteGroup(groupID);
    }

    @Override
    public void exitGroup(int userID, int groupID) {
        groupMapper.exitGroup(userID, groupID);
    }
}
