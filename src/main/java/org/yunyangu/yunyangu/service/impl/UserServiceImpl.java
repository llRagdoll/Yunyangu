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
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

    @Override
    public int updateUserInfo(int userID,
                              String newName,
                              String newAddress,
                              String newEmail,
                              String newPhone,
                              String newSignature) {
        return userMapper.updateUserInfo(userID,newName,newAddress,newEmail,newPhone,newSignature);
    }
}
