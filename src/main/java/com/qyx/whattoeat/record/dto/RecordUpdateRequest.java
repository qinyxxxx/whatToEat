package com.qyx.whattoeat.record.dto;

import com.qyx.whattoeat.record.model.RecordStatus;
import lombok.Data;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Data
public class RecordUpdateRequest {
    private RecordStatus status;
    private String comment; // optional
}
