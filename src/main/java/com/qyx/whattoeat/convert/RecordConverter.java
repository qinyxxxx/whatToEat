package com.qyx.whattoeat.convert;

import com.qyx.whattoeat.dto.RecordDto;
import com.qyx.whattoeat.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Mapper(componentModel = "spring")
public interface RecordConverter {

    @Mapping(source = "id", target = "recordId")
    RecordDto toDto(Record entity);

    @Mapping(source = "recordId", target = "id")
    Record toEntity(RecordDto dto);

    List<RecordDto> toDtoList(List<Record> entities);
}
