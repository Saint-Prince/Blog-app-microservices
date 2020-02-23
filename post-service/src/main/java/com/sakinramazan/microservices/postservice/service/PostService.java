package com.sakinramazan.microservices.postservice.service;

import com.sakinramazan.microservices.postservice.entity.Post;

import java.util.List;

public interface PostService {

    // CRUD ops
    List<Post> getAllPosts();

    Post getPost(Integer id);

    Post createPost(Post post);

    Post updatePost(Post post);

    boolean deletePost(Integer id);

}
