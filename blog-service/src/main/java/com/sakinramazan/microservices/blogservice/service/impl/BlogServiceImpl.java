package com.sakinramazan.microservices.blogservice.service.impl;

import com.sakinramazan.microservices.blogservice.client.PostServiceClient;
import com.sakinramazan.microservices.blogservice.dao.BlogRepository;
import com.sakinramazan.microservices.blogservice.entity.Blog;
import com.sakinramazan.microservices.blogservice.entity.Post;
import com.sakinramazan.microservices.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// TODO -- remove Service annotation ?
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private PostServiceClient postServiceClient;

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
        currBlog.setSubject(blog.getSubject());
        currBlog.setPosts(blog.getPosts());
        return blogRepository.save(currBlog);
    }

    @Override
    public boolean deleteBlog(Integer id) {
        Blog blog = getBlog(id);
        blogRepository.delete(blog);
        return true;
    }

    @Override
    public List<Post> getAllPostOf(Integer id) {
        Blog blog = getBlog(id);
        return blog.getPosts();
    }

    @Override
    public Post addNewPost(Post post, Integer id) {
        Blog blog = getBlog(id);
        Post currPost = postServiceClient.savePost(post);

        // TODO -- refactorable block
        List<Post> posts = blog.getPosts();
        posts.add(currPost);
        blog.setPosts(posts);
        blogRepository.save(blog);

        return currPost;
    }
}
