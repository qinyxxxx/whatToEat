package com.qyx.whattoeat.restaurant.mapper;

import com.qyx.whattoeat.restaurant.model.Restaurant;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Yuxin Qin on 8/27/25
 */
@Mapper
public interface RestaurantMapper {
    int insert(Restaurant restaurant);
}
