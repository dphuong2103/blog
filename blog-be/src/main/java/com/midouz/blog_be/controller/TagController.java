package com.midouz.blog_be.controller;

import com.midouz.blog_be.model.dto.*;
import com.midouz.blog_be.service.BlogService;
import com.midouz.blog_be.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService  tagService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> getAllTags(){
        return tagService.getAllTags();
    }

    @GetMapping("/with-blog-count")
    @ResponseStatus(HttpStatus.OK)
    public List<TagsWithBlogCountDTO> getTagsWithBlogCount(){
        return tagService.getTagsWithBlogCount();
    }
}
