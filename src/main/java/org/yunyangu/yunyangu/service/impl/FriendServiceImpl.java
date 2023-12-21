package org.yunyangu.yunyangu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.mapper.FriendMapper;
import org.yunyangu.yunyangu.service.FriendService;
import org.yunyangu.yunyangu.mapper.UserMapper;


import java.util.List;
import java.util.ArrayList;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getFriend(int userID) {
        List<Integer> friendIds=friendMapper.getFriend(userID);
        List<User> friends = new ArrayList<>(); // 存储好友的 User 对象

        // 遍历好友 ID 列表，获取每个好友的 User 对象
        for (Integer friendId : friendIds) {
            User friend = userMapper.getUserById(friendId);
            if (friend != null) {
                friends.add(friend);
            }
        }
        return friends;
    }

    @Override
    public Integer addFriend(int userID, int friendID) {
        return friendMapper.addFriend(userID,friendID);
    }

    @Override
    public Integer deleteFriend(Integer userID, Integer friendID) {
        return friendMapper.deleteFriend(userID,friendID);
    }

    @Override
    public Integer isFriend(Integer userID, Integer friendID) {
        return friendMapper.isFriend(userID,friendID);
    }
}
