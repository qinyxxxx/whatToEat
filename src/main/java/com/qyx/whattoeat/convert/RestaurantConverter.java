package com.qyx.whattoeat.convert;

import com.qyx.whattoeat.dto.RestaurantDto;
import com.qyx.whattoeat.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Created by Yuxin Qin on 9/1/25
 */
@Mapper(componentModel = "spring")
public interface RestaurantConverter {
    @Mapping(source = "id", target = "restaurantId")
    RestaurantDto toDto(Restaurant entity);

    @Mapping(source = "restaurantId", target = "id")
    Restaurant toEntity(RestaurantDto dto);

}
