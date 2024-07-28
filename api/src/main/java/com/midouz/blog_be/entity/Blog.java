package com.midouz.blog_be.entity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @UuidGenerator
    private String id;
    @Nonnull

    private String title;

    @Nonnull
    private String content;

    @Nullable
    private String summary;

    @OneToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Nonnull
    private String slug;

    @ManyToMany
    @JoinTable(
            name = "blog_tag",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Collection<Tag> tags;

    @Nullable
    @Column(name = "is_published")
    private Boolean isPublished;

    @Nullable
    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "created_at")
    @Nonnull
    private Instant createdAt;

    @Column(name = "updated_at")
    @Nonnull
    private Instant updatedAt;

    @Column(name = "published_at")
    private Instant publishedAt;
}
