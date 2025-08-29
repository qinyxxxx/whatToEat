package com.qyx.whattoeat.record.service;

import com.qyx.whattoeat.common.dto.PageResult;
import com.qyx.whattoeat.record.dto.RecordCreateRequest;
import com.qyx.whattoeat.record.dto.RecordDto;
import com.qyx.whattoeat.record.dto.RecordUpdateRequest;
import com.qyx.whattoeat.record.mapper.RecordMapper;
import com.qyx.whattoeat.record.model.Record;
import com.qyx.whattoeat.restaurant.dto.RestaurantDto;
import com.qyx.whattoeat.restaurant.mapper.RestaurantMapper;
import com.qyx.whattoeat.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Service
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public Long createRecord(RecordCreateRequest request) {
        Long restaurantId = null;

        RestaurantDto dto = request.getRestaurant();
        if (dto.getId() != null) {
            // restaurant already exists
            restaurantId = dto.getId();
        } else {
            // new a restaurant
            Restaurant restaurant = new Restaurant();
            restaurant.setName(dto.getName());
            restaurant.setAddress(dto.getAddress());
            restaurant.setTags(dto.getTags());
            restaurant.setAvgCost(dto.getAvgCost());
            restaurantMapper.insert(restaurant);
            restaurantId = restaurant.getId();
        }

        Record record = new Record();
        record.setUserId(request.getUserId());
        record.setRestaurantId(restaurantId);
        record.setStatus(request.getStatus());
        recordMapper.insert(record);

        return record.getId();
    }

    public PageResult<RecordDto> getRecords(Long userId, String status, int page, int size) {
        int offset = (page - 1) * size;
        List<RecordDto> records = recordMapper.findByStatus(userId, status, size, offset);
        int total = recordMapper.countByStatus(userId, status);

        return new PageResult<>(page, size, total, records);
    }

    public boolean updateRecord(Long recordId, RecordUpdateRequest request) {
        int rows = recordMapper.updateStatus(recordId, request.getStatus().name(), request.getComment());
        return rows > 0;
    }

}
