package org.yunyangu.yunyangu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yunyangu.yunyangu.entity.User;
import org.yunyangu.yunyangu.mapper.UserMapper;
import org.yunyangu.yunyangu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        System.out.println("userId:"+userId);
        return userMapper.getUserById(userId);
    }

    @Override
    public int register(User user) {
        // 检查数据库中是否已存在同名用户
        if (userMapper.existsByUsername(user.getName())) {
            // 如果用户名已存在，返回错误消息
            return -1;
        } else {
            // 如果用户名不存在，执行注册操作
            userMapper.register(user);
            return 0;
        }
    }


    @Override
    public int updateUserInfo(Integer userID,
                              String newName,
                              String newAddress,
                              String newAvatar,
                              String newEmail,
                              String newPhone,
                              String newSignature) {
        System.out.println("userID:"+userID);
        return userMapper.updateUserInfo(userID,newName,newAddress,newAvatar,newEmail,newPhone,newSignature);
    }

    @Override
    public Integer getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
