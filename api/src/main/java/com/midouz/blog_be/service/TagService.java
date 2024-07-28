package com.midouz.blog_be.service;

import com.midouz.blog_be.entity.Tag;
import com.midouz.blog_be.model.dto.TagDTO;
import com.midouz.blog_be.model.dto.TagsWithBlogCountDTO;
import com.midouz.blog_be.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagDTO> getAllTags(){
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(TagDTO::fromTag).toList();
    }

    public List<TagsWithBlogCountDTO> getTagsWithBlogCount(){
        return tagRepository.getTagsWithBlogCount();
    }
}
