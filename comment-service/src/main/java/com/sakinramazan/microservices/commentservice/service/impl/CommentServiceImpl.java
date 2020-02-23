package com.sakinramazan.microservices.commentservice.service.impl;

import com.sakinramazan.microservices.commentservice.dao.CommentRepository;
import com.sakinramazan.microservices.commentservice.entity.Comment;
import com.sakinramazan.microservices.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent())
            throw new RuntimeException("Comment not found");
        return comment.get();
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment commentCurr = getComment(comment.getId());
        commentCurr.setWriter(comment.getWriter());
        commentCurr.setPost(comment.getPost());
        return commentRepository.save(commentCurr);
    }

    @Override
    public boolean deleteComment(Integer id) {
        Comment comment = getComment(id);
        commentRepository.delete(comment);
        return true;
    }
}
