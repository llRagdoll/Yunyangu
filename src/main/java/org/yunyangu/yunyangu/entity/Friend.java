package org.yunyangu.yunyangu.entity;

import lombok.Data;

@Data
public class Friend {
    private Integer userId;
    private Integer friendId;
    private String name;
}
