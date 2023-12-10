package org.yunyangu.yunyangu.service;

import org.yunyangu.yunyangu.entity.User;

public interface UserService {
    User getUserById(int userId);

    int register(User user);

    int updateUserInfo(int userID,
                       String newName,
                       String newAddress,
                       String newEmail,
                       String newPhone,
                       String newSignature);
}
