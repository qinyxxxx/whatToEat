package com.qyx.whattoeat.record.dto;

import com.qyx.whattoeat.record.model.RecordStatus;
import lombok.Data;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Data
public class RecordDto {
    private Long recordId;
    private Long restaurantId;
    private String restaurantName;
    private RecordStatus status;
    private String comment;
}
