package org.yunyangu.yunyangu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunyangu.yunyangu.entity.Message;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @PostMapping("/send")
    public Result send(Message message) {
        int code= messageService.sendMessage(message);
        if(code==1){
            return Result.success();
        }
        else{
            return Result.error("发送失败");
        }
    }

    @GetMapping("/getUserHistory")
    public Result getUserMessage(int userId,int FriendId) {
        List<Message> messageList= messageService.getUserHistory(userId,FriendId);
        return Result.success(messageList);
    }

    @GetMapping("/getGroupHistory")
    public Result getGroupMessage(int userId,int groupId) {
        List<Message> messageList= messageService.getGroupHistory(userId,groupId);
        return Result.success(messageList);
    }

    @PostMapping("/deleteMessage")
    public Result deleteMessage(int messageId) {
        messageService.deleteMessage(messageId);
        return Result.success();
    }
}
