package com.midouz.blog_be.service;

import com.midouz.blog_be.entity.Blog;
import com.midouz.blog_be.entity.Tag;
import com.midouz.blog_be.entity.User;
import com.midouz.blog_be.model.dto.BlogDTO;
import com.midouz.blog_be.model.dto.PageInfo;
import com.midouz.blog_be.model.dto.PaginationResult;
import com.midouz.blog_be.model.exception.BlogNotFoundException;
import com.midouz.blog_be.model.exception.TagNotFoundException;
import com.midouz.blog_be.model.exception.UserNotFoundException;
import com.midouz.blog_be.model.request.CreateBlogRequest;
import com.midouz.blog_be.repository.BlogRepository;
import com.midouz.blog_be.repository.TagRepository;
import com.midouz.blog_be.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final VNCharacterService vnCharacterService;
    private final SlugService slugService;

    public List<BlogDTO> getBlogsByUserId(String userId) {
        List<Blog> blogDTOList = blogRepository.getBlogsByUserId(userId);
        return blogDTOList.stream().map(BlogDTO::fromBlog).toList();
    }

    public BlogDTO createBlog(String userId, CreateBlogRequest request) {
        String slugEn = vnCharacterService.removeAccent(request.getTitle());
        slugEn = slugService.replaceSpecialChar(slugEn);
        String slug = generateUniqueSlug(slugEn);
        Blog blog = request.toBlog(slug);
        blog.setSlug(slug);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        if (!CollectionUtils.isEmpty(request.getTags())) {
            blog.setTags(tagRepository.findAllById(request.getTags()));
        }
        blog.setAuthor(optionalUser.get());
        blogRepository.save(blog);
        return BlogDTO.fromBlog(blog);
    }

    public BlogDTO getBlogBySlug(String slug) throws BlogNotFoundException {
        return blogRepository.findBySlug(slug).map(BlogDTO::fromBlog).orElseThrow(() -> new BlogNotFoundException(BlogNotFoundException.FindBlogType.slug, slug));
    }

    public PaginationResult<BlogDTO> getBlogs(int page, int size, String tagSlug, String q) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        q = q.replace('-', ' ');
        Specification<Blog> specification = createSpecification(tagSlug, q);
        Page<Blog> blogPage = blogRepository.findAll(specification, pageable);
        return PaginationResult.<BlogDTO>builder().pageInfo(PageInfo.builder().next(blogPage.hasNext() ? page + 1 : -1).totalCount((int) blogPage.getTotalElements()).size(size).build()).data(blogPage.stream().map(BlogDTO::fromBlog).toList()).build();
    }

    private Specification<Blog> createSpecification(String tagSlug, String q) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(tagSlug)) {
                Optional<Tag> optionalTag = tagRepository.findByTagNameEn(tagSlug);
                if (optionalTag.isEmpty()) {
                    throw new TagNotFoundException(tagSlug);
                }
                predicates.add(criteriaBuilder.isMember(optionalTag.get(), root.get("tags")));
            }
            if (StringUtils.hasText(q)) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("title"), "%" + q + "%"), criteriaBuilder.like(root.get("content"), "%" + q + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }


    private String generateUniqueSlug(String title) {
        String baseSlug = title.toLowerCase().trim().replace(" ", "-");
        String slug = baseSlug;
        int counter = 0;
        while (blogRepository.existsBySlug(slug)) {
            counter++;
            slug = baseSlug + "-" + counter;
        }
        return slug;
    }

}
