package com.midouz.blog_be.model.dto;
import com.midouz.blog_be.entity.Blog;
import com.midouz.blog_be.entity.User;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class BlogDTO {
    private String id;

    private String title;

    private String content;

    private String summary;

    private UserDTO author;

    private String slug;

    private Boolean isPublished;

    private String metaTitle;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant publishedAt;

    private List<TagDTO> tags;
    public static BlogDTO fromBlog(Blog blog){
        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .summary(blog.getSummary())
                .author(UserDTO.fromUser(blog.getAuthor()))
                .slug(blog.getSlug())
                .isPublished(blog.getIsPublished())
                .metaTitle(blog.getMetaTitle())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .publishedAt(blog.getPublishedAt())
                .tags(blog.getTags().stream().map(TagDTO::fromTag).toList())
                .build();
    }
}
