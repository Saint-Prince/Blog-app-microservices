package com.sakinramazan.microservices.blogservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sakinramazan.microservices.blogservice.client.PostServiceClient;
import com.sakinramazan.microservices.blogservice.dao.BlogRepository;
import com.sakinramazan.microservices.blogservice.entity.Blog;
import com.sakinramazan.microservices.blogservice.entity.Post;
import com.sakinramazan.microservices.blogservice.exception.ResourceNotFoundException;
import com.sakinramazan.microservices.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// TODO -- remove Service annotation ?
@EnableHystrix
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
            throw new ResourceNotFoundException("Blog not found!");
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

    @HystrixCommand(fallbackMethod = "getStaticPosts")
    @Override
    public List<Post> getAllPosts() {
        // Debug on console
        System.out.println("Console out : post-service client called!");
        return postServiceClient.getAllPosts();
    }

    // Hystrix fallback function
    public List<Post> getStaticPosts() {
        // Debug on console
        System.out.println("Console out : Fallback function called!");
        List<Post> staticTestPosts = new ArrayList<>();
        staticTestPosts.add(new Post());
        return staticTestPosts;
    }
}
