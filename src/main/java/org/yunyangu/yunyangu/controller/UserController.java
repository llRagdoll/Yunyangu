package org.yunyangu.yunyangu.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(Integer userId, String password, HttpSession httpSession) {
        User user= userService.getUserById(userId);
        if(password.equals(user.getPassword())){
            httpSession.setAttribute("uid",userId);
            httpSession.setAttribute("username",user.getName());
            return Result.success(user);
        }
        else{
            return Result.error("密码错误");
        }
    }

    @PostMapping("/register")
    public Result register(User user) {
       int code= userService.register(user);
       int userId=user.getUserId();
         if(code==0){
              return Result.success(userId);
         }
         else{
              return Result.error("注册失败,用户名已存在");
         }
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(Integer userId) {
        System.out.println("userId in controller:"+userId);
        User user= userService.getUserById(userId);
        System.out.println("user:"+user);
        return Result.success(user);
    }

    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(Integer userID,
                                 String newName,
                                 String newAddress,
                                 String newAvatar,
                                 String newEmail,
                                 String newPhone,
                                 String newSignature) {
        int code=userService.updateUserInfo(userID,newName,newAddress,newAvatar,newEmail,newPhone,newSignature);
        if(code==1){
            return Result.success();
        }
        else{
            return Result.error("更新失败");
        }
    }

    @GetMapping("/getUserByName")
    public Result getUserInfoByName(String name) {
        Integer userId= userService.getUserByName(name);
        if(userId!=null) {
           return Result.success(userId);
        }else {
            return Result.error("用户不存在");
        }
    }




}
