package com.qyx.whattoeat.service;

import com.qyx.whattoeat.common.dto.PageResult;
import com.qyx.whattoeat.convert.RecordConverter;
import com.qyx.whattoeat.convert.RestaurantConverter;
import com.qyx.whattoeat.model.Record;
import com.qyx.whattoeat.dto.RecordCreateRequest;
import com.qyx.whattoeat.dto.RecordDto;
import com.qyx.whattoeat.dto.RecordUpdateRequest;
import com.qyx.whattoeat.mapper.RecordMapper;
import com.qyx.whattoeat.model.RecordStatus;
import com.qyx.whattoeat.dto.RestaurantDto;
import com.qyx.whattoeat.mapper.RestaurantMapper;
import com.qyx.whattoeat.model.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {
    @Autowired
    private final RecordMapper recordMapper;

    @Autowired
    private final RestaurantMapper restaurantMapper;

    @Autowired
    private final RecordConverter recordConverter;

    @Autowired
    private final RestaurantConverter restaurantConverter;


    public Long createRecord(RecordCreateRequest request) {
        Long restaurantId = null;

        RestaurantDto dto = request.getRestaurant();
        if (dto.getRestaurantId() != null) {
            // restaurant already exists
            restaurantId = dto.getRestaurantId();
        } else {
            // new a restaurant
            Restaurant existed = restaurantMapper.findByNameAndAddress(dto.getName(), dto.getAddress());
            if (existed != null){
                log.info("Restaurant already exists: id={}, name={}, address={}",
                        existed.getId(), existed.getName(), existed.getAddress());
                restaurantId = existed.getId();
            } else{
                Restaurant restaurant = new Restaurant();
                restaurant.setName(dto.getName());
                restaurant.setAddress(dto.getAddress());
                restaurant.setTags(dto.getTags());
                restaurant.setAvgCost(dto.getAvgCost());
                restaurantMapper.insert(restaurant);
                restaurantId = restaurant.getId();
            }
        }

        Record record = new Record();
        record.setUserId(request.getUserId());
        record.setRestaurantId(restaurantId);
        record.setStatus(request.getStatus());
        if (request.getStatus() == RecordStatus.WISH) {
            record.setRating(null);
        } else if (request.getRating() != null) {
            int r = request.getRating();
            if (r < 1 || r > 5) throw new IllegalArgumentException("rating must be 1..5");
            record.setRating(r);
        }
        recordMapper.insert(record);

        return record.getId();
    }

    public PageResult<RecordDto> getRecords(Long userId, RecordStatus status, int page, int size) {
        int offset = (page - 1) * size;
        List<Record> records = recordMapper.findByStatus(userId, status, size, offset);
        List<RecordDto> recordDtos = new ArrayList<>();
        for (Record r: records) {
            recordDtos.add(convertToRecordDto(r));
        }
        int total = recordMapper.countByStatus(userId, status);
        return new PageResult<>(page, size, total, recordDtos);
    }

    public boolean updateRecord(Long recordId, RecordUpdateRequest request) {
        if (request.getRating() != null) {
            int r = request.getRating();
            if (r < 1 || r > 5) throw new IllegalArgumentException("rating must be 1..5");
        }
        if (request.getStatus() == RecordStatus.WISH) {
            request.setRating(null);
        }
        int rows = recordMapper.updateStatus(recordId, request.getStatus().name(), request.getComment());
        return rows > 0;
    }

    private RecordDto convertToRecordDto(Record record){
        Long restId = record.getRestaurantId();
        Restaurant restaurant = restaurantMapper.findById(restId);

        RecordDto recordDto = recordConverter.toDto(record);
        recordDto.setRestaurant(restaurantConverter.toDto(restaurant));
        return recordDto;
    }
}
