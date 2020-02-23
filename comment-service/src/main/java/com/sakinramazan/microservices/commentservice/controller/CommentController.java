package com.sakinramazan.microservices.commentservice.controller;

import com.sakinramazan.microservices.commentservice.entity.Comment;
import com.sakinramazan.microservices.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(commentService.getComment(id));
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment, Errors errors) {
        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
    }

    @PutMapping("/comments")
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment, Errors errors) {
        return new ResponseEntity<>(commentService.updateComment(comment), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable(value = "id") Integer id) {
        Comment Comment = commentService.getComment(id);
        commentService.deleteComment(Comment.getId());

        return new ResponseEntity<>(new HashMap<>().put("deleted", Boolean.TRUE), HttpStatus.OK);
    }
}
