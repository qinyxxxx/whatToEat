package com.qyx.whattoeat.record.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Data
public class Record {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private RecordStatus status;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
