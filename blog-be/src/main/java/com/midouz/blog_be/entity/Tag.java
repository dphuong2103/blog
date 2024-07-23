package com.midouz.blog_be.entity;

import jakarta.annotation.Nonnull;
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
@Table(name = "tag")
public class Tag {
    @Id
    @UuidGenerator
    private String id;

    @Nonnull
    @Column(name="tag_name_en")
    private String tagNameEn;

    @Nonnull
    @Column(name="tag_name_vn")
    private String tagNameVn;

    @Column(name = "created_at")
    @Nonnull
    private Instant createdAt;

    @ManyToMany
    @JoinTable(
            name = "blog_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id")
    )
    Collection<Blog> blogs;
}
