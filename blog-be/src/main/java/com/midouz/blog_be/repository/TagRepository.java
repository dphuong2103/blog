package com.midouz.blog_be.repository;

import com.midouz.blog_be.entity.Tag;
import com.midouz.blog_be.model.dto.TagsWithBlogCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    @Query("SELECT t FROM Tag t WHERE t.tagNameEn = :tagNameEn")
    Optional<Tag> findByTagNameEn(String tagNameEn);

    @Query("SELECT new com.midouz.blog_be.model.dto.TagsWithBlogCountDTO(t, COUNT(b)) " +
            "FROM Tag t JOIN t.blogs b " +
            "GROUP BY t")
    List<TagsWithBlogCountDTO> getTagsWithBlogCount();
}
