package org.yunyangu.yunyangu.service;

import java.util.List;

public interface GroupService {
    List<Integer> getUserGroup(int userID);

    Object getGroupInfo(int groupID);

    int createGroup(int userID, String groupName);

    int joinGroup(int userID, int groupID);

    int deleteGroup(int groupID);

    void exitGroup(int userID, int groupID);
}
