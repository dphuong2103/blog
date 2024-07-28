package com.midouz.blog_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageInfo {
    private int next;
    private int totalCount;
    private int size;
}
