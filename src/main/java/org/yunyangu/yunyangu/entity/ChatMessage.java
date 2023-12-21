package org.yunyangu.yunyangu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessage {
    Integer from;
    Integer to;
    String text;
    String fromName;
    Date date;
}
