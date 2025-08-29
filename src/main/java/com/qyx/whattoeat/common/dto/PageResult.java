package com.qyx.whattoeat.common.dto;

import lombok.Data;
import java.util.List;

/**
 * Created by Yuxin Qin on 8/28/25
 */

@Data
public class PageResult<T> {
    private int page;
    private int size;
    private int total;
    private List<T> items;

    public PageResult(int page, int size, int total, List<T> items) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.items = items;
    }
}
