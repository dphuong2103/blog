package com.midouz.blog_be.model.exception;

public class BlogNotFoundException extends Exception{
    public enum FindBlogType {
        slug,
        id,
    }
    public BlogNotFoundException(FindBlogType type, String value) {
        super(
                type == FindBlogType.slug ? "Blog with slug " + value + " not found" :  "Blog with id " + value + " not found"
        );
    }

}
