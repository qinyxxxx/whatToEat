package com.qyx.whattoeat.service;

import com.qyx.whattoeat.dto.RestaurantCreateRequest;
import com.qyx.whattoeat.mapper.RestaurantMapper;
import com.qyx.whattoeat.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yuxin Qin on 8/27/25
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    public Restaurant create(RestaurantCreateRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setTags(request.getTags());
        restaurant.setAvgCost(request.getAvgCost());
        restaurantMapper.insert(restaurant);
        return restaurant;
    }


}
