package org.yunyangu.yunyangu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunyangu.yunyangu.mapper.FriendMapper;
import org.yunyangu.yunyangu.service.FriendService;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Override
    public List<Integer> getFriend(int userID) {
        return friendMapper.getFriend(userID);
    }

    @Override
    public Integer addFriend(int userID, int friendID) {
        return friendMapper.addFriend(userID,friendID);
    }

    @Override
    public Integer deleteFriend(int userID, int friendID) {
        return friendMapper.deleteFriend(userID,friendID);
    }
}
