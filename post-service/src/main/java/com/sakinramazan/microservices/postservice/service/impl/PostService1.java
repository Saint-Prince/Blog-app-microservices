package com.sakinramazan.microservices.postservice.service.impl;

import com.sakinramazan.microservices.postservice.dao.PostRepository;
import com.sakinramazan.microservices.postservice.entity.Post;
import com.sakinramazan.microservices.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostService1 implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent())
            throw new RuntimeException("Post not found!");
        return post.get();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        Post post1 = getPost(post.getId());
        post1.setWriter(post.getWriter());
        post1.setComments(post.getComments());
        return postRepository.save(post1);
    }

    @Override
    public boolean deletePost(Integer id) {
        Post post = getPost(id);
        postRepository.delete(post);
        return true;
    }
}
