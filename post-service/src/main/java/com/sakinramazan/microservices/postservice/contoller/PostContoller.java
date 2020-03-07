package com.sakinramazan.microservices.postservice.contoller;


import com.sakinramazan.microservices.postservice.entity.Post;
import com.sakinramazan.microservices.postservice.kafka.KafkaProducer;
import com.sakinramazan.microservices.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostContoller {

    @Autowired
    private PostService postService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    // Conditional Caching - If the incoming post's subject is not 'Sample subject 1', cache it
    @Cacheable(value = "posts", key = "#id", unless = "#result.body.blog.subject != 'Sample subject 1'")
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(postService.getPost(id));
    }

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    // CachePut for not deleting , just adding new one
    @CachePut(value = "posts", key = "#post.id")
    @PutMapping("/")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
    }

    // CacheEvit for delete all first then put new ones
    @CacheEvict(value = "posts", allEntries = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Integer id) {
        Post post = postService.getPost(id);
        postService.deletePost(post.getId());
        return new ResponseEntity<>(new HashMap<>().put("deleted", Boolean.TRUE), HttpStatus.OK);
    }

    @GetMapping("/send-kafka/{message}")
    public ResponseEntity<Object> senMessageonKafka(@PathVariable(value = "message") String message) {
        kafkaProducer.sendMessage("sampletopic.t", message);
        return new ResponseEntity<>(new HashMap<>().put("message-sent-status", Boolean.TRUE), HttpStatus.OK);
    }

}
