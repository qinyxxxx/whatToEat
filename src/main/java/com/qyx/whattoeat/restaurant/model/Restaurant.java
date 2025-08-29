package com.qyx.whattoeat.restaurant.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Yuxin Qin on 8/27/25
 */
@Data
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String tags;
    private BigDecimal avgCost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
