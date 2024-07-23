package com.midouz.blog_be.model.dto;

import com.midouz.blog_be.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TagsWithBlogCountDTO {
    private TagDTO tag;
    private Long count;

    public TagsWithBlogCountDTO(Tag tag, Long count) {
        this.tag = TagDTO.fromTag(tag);
        this.count = count;
    }
}
