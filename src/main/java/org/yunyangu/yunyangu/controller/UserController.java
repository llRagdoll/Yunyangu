package org.yunyangu.yunyangu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunyangu.yunyangu.entity.Result;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(int userId, String password) {
        User user= userService.getUserById(userId);
        if(password.equals(user.getPassword())){
            return Result.success();
        }
        else{
            return Result.error("密码错误");
        }
    }

    @PostMapping("/register")
    public Result register(User user) {
       int code= userService.register(user);
       int userId=user.getUserId();
         if(code==1){
              return Result.success(userId);
         }
         else{
              return Result.error("注册失败");
         }
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(int userId) {
        User user= userService.getUserById(userId);
        return Result.success(user);
    }

    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(int userID,
                                 String newName,
                                 String newAddress,
                                 String newEmail,
                                 String newPhone,
                                 String newSignature) {
        int code=userService.updateUserInfo(userID,newName,newAddress,newEmail,newPhone,newSignature);
        if(code==1){
            return Result.success();
        }
        else{
            return Result.error("更新失败");
        }
    }

}
