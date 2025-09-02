package com.qyx.whattoeat.dto;

import com.qyx.whattoeat.model.RecordStatus;
import lombok.Data;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Data
public class RecordDto {
    private Long recordId;
    private Long restaurantId;
    private RecordStatus status;
    private Integer rating;
    private String comment;
    private RestaurantDto restaurant;
}
