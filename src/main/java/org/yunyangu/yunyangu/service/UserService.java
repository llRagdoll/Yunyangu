package org.yunyangu.yunyangu.service;

import org.yunyangu.yunyangu.entity.User;

public interface UserService {
    User getUserById(Integer userId);

    int register(User user);

    int updateUserInfo(Integer userID,
                       String newName,
                       String newAddress,
                       String newAvatar,
                       String newEmail,
                       String newPhone,
                       String newSignature);

    Integer getUserByName(String name);
}
