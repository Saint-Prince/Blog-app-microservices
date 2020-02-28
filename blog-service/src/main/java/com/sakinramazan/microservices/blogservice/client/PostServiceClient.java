package com.sakinramazan.microservices.blogservice.client;

import com.sakinramazan.microservices.blogservice.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("post-service")
public interface PostServiceClient {

    @PostMapping(value = "/")
    Post savePost(@Valid @RequestBody Post post);

}
