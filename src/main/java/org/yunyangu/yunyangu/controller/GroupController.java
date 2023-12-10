package org.yunyangu.yunyangu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public Result createGroup(int userID,String groupName ){
        int code=groupService.createGroup(userID,groupName);
        if(code==1){
            return Result.success();
        }
        else{
            return Result.error("创建失败");
        }
    }

    @PostMapping("/join")
    public Result joinGroup(int userID,int groupID){
        int code=groupService.joinGroup(userID,groupID);
        if(code==1){
            return Result.success();
        }
        else{
            return Result.error("加入失败");
        }
    }

    @GetMapping("/getUserGroup")
    public Result getUserGroup(int userID){
        List<Integer> temp=groupService.getUserGroup(userID);
        if(temp.isEmpty()){
            return Result.error("用户没有群组");
        }
        else{
            return Result.success(temp);
        }
    }

    @GetMapping("/getGroupInfo")
    public Result getGroupInfo(int groupID){
        return Result.success(groupService.getGroupInfo(groupID));
    }

    @PostMapping("/deleteGroup")
    public Result deleteGroup(int groupID){
        groupService.deleteGroup(groupID);
        return Result.success();
    }

    @PostMapping("/exitGroup")
    public Result exitGroup(int userID,int groupID){
        groupService.exitGroup(userID,groupID);
        return Result.success();
    }
}
