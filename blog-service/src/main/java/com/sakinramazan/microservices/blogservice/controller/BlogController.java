package com.sakinramazan.microservices.blogservice.controller;

import com.sakinramazan.microservices.blogservice.entity.Blog;
import com.sakinramazan.microservices.blogservice.entity.Post;
import com.sakinramazan.microservices.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(blogService.getBlog(id));
    }

    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog, Errors errors) {
        return new ResponseEntity<>(blogService.createBlog(blog), HttpStatus.CREATED);
    }

    @PutMapping("/blogs")
    public ResponseEntity<Blog> updateBlog(@Valid @RequestBody Blog blog, Errors errors) {
        return new ResponseEntity<>(blogService.updateBlog(blog), HttpStatus.OK);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Object> deleteBlog(@PathVariable(value = "id") Integer id) {
        Blog blog = blogService.getBlog(id);
        blogService.deleteBlog(blog.getId());

        return new ResponseEntity<>(new HashMap<>().put("deleted", Boolean.TRUE), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<List<Post>> getBlogs(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(blogService.getAllPostOf(id), HttpStatus.OK);
    }

    @PostMapping("/blogs")
    public ResponseEntity<Post> addNewPost(@PathVariable(value = "id") Integer id, @Valid @RequestBody Post post, Errors errors) {
        return new ResponseEntity<>(blogService.addNewPost(post, id), HttpStatus.CREATED);
    }


}