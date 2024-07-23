package com.midouz.blog_be.model.dto;

import com.midouz.blog_be.entity.Tag;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Builder
@Data
public class TagDTO {
    private String id;
    private String tagNameEn;
    private String tagNameVn;
    private Instant createdAt;

    public static TagDTO fromTag(Tag tag){
        return TagDTO.builder()
                .id(tag.getId())
                .tagNameEn(tag.getTagNameEn())
                .tagNameVn(tag.getTagNameVn())
                .createdAt(tag.getCreatedAt())
                .build();
    }
}
