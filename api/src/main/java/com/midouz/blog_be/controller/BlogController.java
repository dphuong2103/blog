package com.midouz.blog_be.controller;

import com.midouz.blog_be.entity.User;
import com.midouz.blog_be.model.dto.BlogDTO;
import com.midouz.blog_be.model.dto.PaginationResult;
import com.midouz.blog_be.model.exception.BlogNotFoundException;
import com.midouz.blog_be.model.request.CreateBlogRequest;
import com.midouz.blog_be.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDTO> getBlogsByUserId(@PathVariable String userId) {
        return blogService.getBlogsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO createBlog(@RequestBody CreateBlogRequest request, @AuthenticationPrincipal User userDetails) {
        String userId = userDetails.getId();
        return blogService.createBlog(userId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginationResult<BlogDTO> getBlogs(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String tagSlug, @RequestParam(defaultValue = "") String q) {
        int size =10;
        return blogService.getBlogs(page, size, tagSlug,q);
    }

    @GetMapping("{slug}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDTO getBlogBySlug(@PathVariable String slug) throws BlogNotFoundException {
        return blogService.getBlogBySlug(slug);
    }

}
