package com.prog4.repository;

import com.prog4.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByNameOfPostIgnoreCase(String name);
}
