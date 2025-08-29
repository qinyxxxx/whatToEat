package com.qyx.whattoeat.restaurant.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Yuxin Qin on 8/27/25
 */
@Data
public class RestaurantCreateRequest {
    private String name;
    private String address;
    private String tags;
    private BigDecimal avgCost;

}