package com.midouz.blog_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PaginationResult<T> {
    private PageInfo pageInfo;
    private List<T> data;
}
