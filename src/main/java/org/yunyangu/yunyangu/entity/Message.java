package org.yunyangu.yunyangu.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Integer msgId;
    private String content;
    private Integer msgFrom;
    private Integer msgTo;
    private LocalDateTime msgTime;
    private Integer msgType;
}
