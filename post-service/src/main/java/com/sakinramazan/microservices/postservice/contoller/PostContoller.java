package com.sakinramazan.microservices.postservice.contoller;


import com.sakinramazan.microservices.postservice.entity.Post;
import com.sakinramazan.microservices.postservice.kafka.KafkaProducer;
import com.sakinramazan.microservices.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(postService.getPost(id));
    }

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Integer id) {
        Post post = postService.getPost(id);
        postService.deletePost(post.getId());
        return new ResponseEntity<>(new HashMap<>().put("deleted", Boolean.TRUE), HttpStatus.OK);
    }

    @GetMapping("/send-kafka")
    public ResponseEntity<Object> senMessageonKafka() {
        kafkaProducer.sendMessage("Sample Topic", "Sample Test Message");
        return new ResponseEntity<>(new HashMap<>().put("message-sent-status", Boolean.TRUE), HttpStatus.OK);
    }

}
