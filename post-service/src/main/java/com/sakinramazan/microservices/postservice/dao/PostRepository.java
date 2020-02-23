package com.sakinramazan.microservices.postservice.dao;

import com.sakinramazan.microservices.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
