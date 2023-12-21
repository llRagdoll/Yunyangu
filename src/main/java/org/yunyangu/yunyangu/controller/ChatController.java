package org.yunyangu.yunyangu.controller;

import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.yunyangu.yunyangu.entity.ChatMessage;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.service.FriendService;
import org.yunyangu.yunyangu.service.UserService;
import org.yunyangu.yunyangu.websocket.MyWebSocketHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @Autowired
    MyWebSocketHandler handler;

    @PostMapping("/onlinefriends")
    @ResponseBody
    public Set<String> onlinefriends(HttpSession session) {
        //System.out.println("onlinefriends111");
        Map<Integer, WebSocketSession> map = MyWebSocketHandler.userSocketSessionMap;
        Integer currentUserId=(Integer) session.getAttribute("uid");
        List<User> temp=friendService.getFriend(currentUserId);
        System.out.println("zheshionlineusers");
        //System.out.println(temp);

        return map.keySet().stream()
                .filter(userId -> temp.stream().anyMatch(user -> user.getUserId().equals(userId)))
                .map(userId -> userService.getUserById(userId).getName())
                .collect(Collectors.toSet());
    }



    @PostMapping("/broadcastGroup")
    @ResponseBody
    public Result broadcastGroup(String fromName, String groupName, String text) {
        System.out.println("broadcastGroup");
        System.out.println(fromName);
        System.out.println(groupName);
        System.out.println(text);
        ChatMessage msg = new ChatMessage();
        msg.setDate(new Date());
        msg.setFrom(-1);// -1表示群聊
        msg.setFromName(fromName);
        msg.setTo(0);
        msg.setText(text);
        handler.broadcastGroup(groupName,new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
        return Result.success();
    }


}
