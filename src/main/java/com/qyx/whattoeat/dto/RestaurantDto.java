package com.qyx.whattoeat.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Data
public class RestaurantDto {
    private Long restaurantId;
    private String name;
    private String address;
    private String tags;
    private BigDecimal avgCost;
}
