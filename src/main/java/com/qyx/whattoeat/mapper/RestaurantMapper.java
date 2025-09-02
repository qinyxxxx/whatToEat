package com.qyx.whattoeat.mapper;

import com.qyx.whattoeat.model.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Yuxin Qin on 8/27/25
 */
@Mapper
public interface RestaurantMapper {
    int insert(Restaurant restaurant);

    Restaurant findByNameAndAddress(@Param("name") String name, @Param("address") String address);

    Restaurant findById(@Param("restaurantId") Long restaurantId);
}
