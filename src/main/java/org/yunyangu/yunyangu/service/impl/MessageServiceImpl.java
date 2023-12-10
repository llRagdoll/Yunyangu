package org.yunyangu.yunyangu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunyangu.yunyangu.entity.Message;
import org.yunyangu.yunyangu.mapper.MessageMapper;
import org.yunyangu.yunyangu.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public int sendMessage(Message message) {
        if(message.getMsgType()==1){
            return messageMapper.sendUserMessage(message);
        }else{
            return messageMapper.sendGroupMessage(message);
        }
    }

    @Override
    public List<Message> getUserHistory(int userId, int friendId) {
        return messageMapper.getUserHistory(userId,friendId);
    }

    @Override
    public List<Message> getGroupHistory(int userId,int groupId) {
        return messageMapper.getGroupHistory(userId,groupId);
    }

    @Override
    public Integer deleteMessage(int messageId) {
        return messageMapper.deleteMessage(messageId);
    }
}
