package com.qyx.whattoeat.record.controller;

import com.qyx.whattoeat.common.dto.ApiResponse;
import com.qyx.whattoeat.common.dto.PageResult;
import com.qyx.whattoeat.record.dto.RecordCreateRequest;
import com.qyx.whattoeat.record.dto.RecordDto;
import com.qyx.whattoeat.record.dto.RecordUpdateRequest;
import com.qyx.whattoeat.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yuxin Qin on 8/28/25
 */
@RestController
@RequestMapping("/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping
    public ApiResponse<Long> createRecord(@RequestBody RecordCreateRequest request) {
        try {
            Long id = recordService.createRecord(request);
            return ApiResponse.success(id);
        } catch (Exception e) {
            return ApiResponse.error("Failed to create record: " + e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<PageResult<RecordDto>> getRecords(
            @RequestParam Long userId,
            @RequestParam String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResult<RecordDto> result = recordService.getRecords(userId, status, page, size);
        return ApiResponse.success(result);
    }

    @PutMapping("/{recordId}")
    public ApiResponse<Boolean> updateRecord(
            @PathVariable Long recordId,
            @RequestBody RecordUpdateRequest request
    ) {
        boolean success = recordService.updateRecord(recordId, request);
        if (success) {
            return ApiResponse.success(true);
        } else {
            return ApiResponse.error("Record not found or update failed");
        }
    }
}
