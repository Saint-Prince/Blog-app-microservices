package com.sakinramazan.microservices.blogservice.service.impl;

import com.sakinramazan.microservices.blogservice.dao.BlogRepository;
import com.sakinramazan.microservices.blogservice.entity.Blog;
import com.sakinramazan.microservices.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlog(Integer id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (!blog.isPresent())
            throw new RuntimeException("Blog not found!");
        return blog.get();
    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        Blog currBlog = getBlog(blog.getId());
        currBlog.setBlog_subject(blog.getBlog_subject());
        currBlog.setPosts(blog.getPosts());
        return blogRepository.save(currBlog);
    }

    @Override
    public boolean deleteBlog(Integer id) {
        Blog blog = getBlog(id);
        blogRepository.delete(blog);
        return true;
    }
}
