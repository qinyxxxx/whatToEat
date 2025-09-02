package com.qyx.whattoeat.controller;

import com.qyx.whattoeat.auth.CurrentUser;
import com.qyx.whattoeat.common.dto.ApiResponse;
import com.qyx.whattoeat.common.dto.PageResult;
import com.qyx.whattoeat.dto.RecordCreateRequest;
import com.qyx.whattoeat.dto.RecordUpdateRequest;
import com.qyx.whattoeat.dto.RecordDto;
import com.qyx.whattoeat.model.RecordStatus;
import com.qyx.whattoeat.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yuxin Qin on 8/28/25
 */

@Slf4j
@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @PostMapping
    public ApiResponse<Long> createRecord(@RequestBody RecordCreateRequest request) {
        try {
            Long userId = CurrentUser.id();
            Long id = recordService.createRecord(userId, request);
            return ApiResponse.success(id);
        } catch (Exception e) {
            log.error("Failed to create record: " + e.getMessage());
            return ApiResponse.error("Failed to create record: " + e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse<PageResult<RecordDto>> getRecords(
            @RequestParam RecordStatus status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Long userId = CurrentUser.id();
        PageResult<RecordDto> result = recordService.getRecords(userId, status, page, size);
        return ApiResponse.success(result);
    }

    @PutMapping("/{recordId}")
    public ApiResponse<Boolean> updateRecord(
            @PathVariable Long recordId,
            @RequestBody RecordUpdateRequest request
    ) {
        Long userId = CurrentUser.id();
        boolean success = recordService.updateRecord(recordId, userId, request);
        if (success) {
            return ApiResponse.success(true);
        } else {
            return ApiResponse.error("Record not found or update failed");
        }
    }
}
