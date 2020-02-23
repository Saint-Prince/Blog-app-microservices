package com.sakinramazan.microservices.commentservice.service;

import com.sakinramazan.microservices.commentservice.entity.Comment;

import java.util.List;

public interface CommentService {

    // CRUD ops
    List<Comment> getAllComments();

    Comment getComment(Integer id);

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    boolean deleteComment(Integer id);

}
