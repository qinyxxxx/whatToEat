package com.qyx.whattoeat.restaurant.controller;

import com.qyx.whattoeat.common.dto.ApiResponse;
import com.qyx.whattoeat.restaurant.dto.RestaurantCreateRequest;
import com.qyx.whattoeat.restaurant.model.Restaurant;
import com.qyx.whattoeat.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yuxin Qin on 8/27/25
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<Restaurant> create(@RequestBody RestaurantCreateRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return ApiResponse.error("Restaurant name cannot be empty");
        }
        Restaurant restaurant = restaurantService.create(request);
        return ApiResponse.success(restaurant);
    }
}
