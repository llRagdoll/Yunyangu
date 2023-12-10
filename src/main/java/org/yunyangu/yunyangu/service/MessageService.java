package org.yunyangu.yunyangu.service;

import org.yunyangu.yunyangu.entity.Message;

import java.util.List;

public interface MessageService {
    int sendMessage(Message message);

    List<Message> getUserHistory(int userId, int friendId);

    List<Message> getGroupHistory(int userId,int groupId);

    Integer deleteMessage(int messageId);
}
