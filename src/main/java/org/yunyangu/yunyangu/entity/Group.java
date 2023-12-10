package org.yunyangu.yunyangu.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Group {
    private int groupId;
    private String name;
    private int groupMaster;
    private String avatar;
    private LocalDateTime createTime;
}
