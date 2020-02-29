package com.sakinramazan.microservices.blogservice.client;

import com.sakinramazan.microservices.blogservice.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient("post-service")
public interface PostServiceClient {

    @GetMapping("/posts/")
    List<Post> getAllPosts();

    @PostMapping("/posts/")
    Post savePost(@Valid @RequestBody Post post);
}
