package org.yunyangu.yunyangu.service;

import java.util.List;

public interface FriendService {
    List<Integer> getFriend(int userID);

    Integer addFriend(int userID, int friendID);

    Integer deleteFriend(int userID, int friendID);
}
