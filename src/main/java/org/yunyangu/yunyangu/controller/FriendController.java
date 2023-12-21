package org.yunyangu.yunyangu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunyangu.yunyangu.entity.Friend;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.service.FriendService;
import org.yunyangu.yunyangu.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @GetMapping("/getFriend")
    public Result getFriend(int userID){
        List<User> temp=friendService.getFriend(userID);
        return Result.success(temp);
    }

    @PostMapping("/addFriend")
    public Result addFriend(int userID,int friendID){
        friendService.addFriend(userID,friendID);
        friendService.addFriend(friendID,userID);
        return Result.success();
    }

    @PostMapping("/deleteFriend")
    public Result deleteFriend(Integer userID,Integer friendID){
        friendService.deleteFriend(userID,friendID);
        friendService.deleteFriend(friendID,userID);
        return Result.success();
    }

    @GetMapping("/isFriend")
    public Result isFriend(Integer userId,String friendName){
        System.out.println("isFriend");
        System.out.println(userId);
        System.out.println(friendName);
        Integer friendID=userService.getUserByName(friendName);
        Integer temp=friendService.isFriend(userId,friendID);
        if(temp==null){
            return Result.error("不是好友");
        }
        else{
            return Result.success();
        }
    }
}
