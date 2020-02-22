package com.sakinramazan.microservices.blogservice.client;

import com.sakinramazan.microservices.blogservice.entity.Post;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("post-service")
public interface PostServiceClient {

    @PostMapping(value = "/post")
    Post savePost(@Valid @RequestBody Post post);

}
