package com.qyx.whattoeat.record.mapper;

import com.qyx.whattoeat.record.dto.RecordDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.qyx.whattoeat.record.model.Record;

import java.util.List;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@Mapper
public interface RecordMapper {
    int insert(Record record);

    List<RecordDto> findByStatus(
            @Param("userId") Long userId,
            @Param("status") String status,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int countByStatus(@Param("userId") Long userId, @Param("status") String status);

    int updateStatus(
            @Param("recordId") Long recordId,
            @Param("status") String status,
            @Param("comment") String comment
    );
}