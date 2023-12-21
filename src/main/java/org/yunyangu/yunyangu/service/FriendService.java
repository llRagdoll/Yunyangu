package org.yunyangu.yunyangu.service;

import org.yunyangu.yunyangu.entity.Friend;
import org.yunyangu.yunyangu.entity.User;

import java.util.List;

public interface FriendService {
    List<User> getFriend(int userID);

    Integer addFriend(int userID, int friendID);

    Integer deleteFriend(Integer userID, Integer friendID);

    Integer isFriend(Integer userID, Integer friendID);
}
