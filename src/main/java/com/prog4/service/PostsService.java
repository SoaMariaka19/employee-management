package com.prog4.service;

import com.prog4.model.Posts;
import com.prog4.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostsService {
    private final PostsRepository repository;

    public List<Posts> findAll(){
        return repository.findAll();
    }

    public Posts getById(Long id){
        Optional<Posts> optionalPosts = repository.findById(id);
        Posts posts = new Posts();

        optionalPosts.ifPresent(value -> posts.setNameOfPost(value.getNameOfPost()));
        posts.setId(id);

        return posts;
    }
}
