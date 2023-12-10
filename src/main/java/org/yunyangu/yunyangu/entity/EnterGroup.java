package org.yunyangu.yunyangu.entity;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

@Data
public class EnterGroup {
    private Integer userId;
    private Integer groupId;
    private LocalDateTime enterTime;
    private String role;
}
