package org.yunyangu.yunyangu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.service.FriendService;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @GetMapping("/getFriend")
    public Result getFriend(int userID){
        List<Integer> temp=friendService.getFriend(userID);
        return Result.success(temp);
    }

    @PostMapping("/addFriend")
    public Result addFriend(int userID,int friendID){
        friendService.addFriend(userID,friendID);
        friendService.addFriend(friendID,userID);
        return Result.success();
    }

    @PostMapping("/deleteFriend")
    public Result deleteFriend(int userID,int friendID){
        friendService.deleteFriend(userID,friendID);
        friendService.deleteFriend(friendID,userID);
        return Result.success();
    }
}
