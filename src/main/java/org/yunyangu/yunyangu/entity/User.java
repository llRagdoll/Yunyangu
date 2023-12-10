package org.yunyangu.yunyangu.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String name;
    private String password;
    private String avatar;
    private String email;
    private String phone;
    private String address;
    private String signature;
}
