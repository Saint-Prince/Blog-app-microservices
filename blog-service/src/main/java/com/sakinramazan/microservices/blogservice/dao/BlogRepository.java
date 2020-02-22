package com.sakinramazan.microservices.blogservice.dao;

import com.sakinramazan.microservices.blogservice.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
