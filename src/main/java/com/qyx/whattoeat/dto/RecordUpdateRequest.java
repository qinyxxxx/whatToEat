package com.qyx.whattoeat.dto;

import com.qyx.whattoeat.model.RecordStatus;
import lombok.Data;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Data
public class RecordUpdateRequest {
    private RecordStatus status;
    private Integer rating;
    private String comment; // optional
}
