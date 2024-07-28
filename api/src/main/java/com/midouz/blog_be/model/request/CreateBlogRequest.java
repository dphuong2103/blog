package com.midouz.blog_be.model.request;
import com.midouz.blog_be.entity.Blog;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CreateBlogRequest {
    @Nonnull
    private String title;
    @Nonnull
    private String content;
    @Nonnull
    private String summary;
    @Nonnull
    private Boolean isPublished;

    private List<String> tags;

    public Blog toBlog(String slug){
        Instant now = Instant.now();
        return Blog.builder()
                .title(this.title)
                .content(this.content)
                .summary(this.summary)
                .metaTitle(this.title)
                .slug(slug)
                .isPublished(this.isPublished)
                .createdAt(now)
                .updatedAt(now)
                .publishedAt(this.isPublished ? now : null)
                .build();
    }
}
