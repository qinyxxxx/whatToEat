package com.qyx.whattoeat.record.dto;

/**
 * Created by Yuxin Qin on 8/28/25
 */

import com.qyx.whattoeat.record.model.RecordStatus;
import com.qyx.whattoeat.restaurant.dto.RestaurantDto;
import lombok.Data;

@Data
public class RecordCreateRequest {
    private Long userId;
    private RestaurantDto restaurant;
    private RecordStatus status;        // WISH or VISITED
    private Integer rating;
}
