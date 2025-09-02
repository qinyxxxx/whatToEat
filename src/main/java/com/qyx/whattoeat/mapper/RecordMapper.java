package com.qyx.whattoeat.mapper;

import com.qyx.whattoeat.model.Record;
import com.qyx.whattoeat.model.RecordStatus;
import com.qyx.whattoeat.dto.RecordDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Mapper
public interface RecordMapper {
    int insert(Record record);

    List<Record> findByStatus(
            @Param("userId") Long userId,
            @Param("status") RecordStatus status,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int countByStatus(@Param("userId") Long userId, @Param("status") RecordStatus status);

    int updateStatus(
            @Param("recordId") Long recordId,
            @Param("status") String status,
            @Param("comment") String comment
    );

    Record findByUserAndRestaurant(@Param("userId") Long userId,
                                   @Param("restaurantId") Long restaurantId);

    Record findById(@Param("recordId") Long recordId);

}