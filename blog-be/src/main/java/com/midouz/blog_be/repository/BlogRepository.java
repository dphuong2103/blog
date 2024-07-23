package com.midouz.blog_be.repository;

import com.midouz.blog_be.entity.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,String>, JpaSpecificationExecutor<Blog> {
    @Query("SELECT b FROM Blog b WHERE b.author.id = :userId")
    List<Blog> getBlogsByUserId(String userId);

    @Query("SELECT b FROM Blog b WHERE b.slug = :slug")
    Optional<Blog> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
