package com.prog4.service;

import com.prog4.entity.Post;
import com.prog4.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostsService {
    private final PostsRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post getById(Long id){
        Optional<Post> optionalPosts = repository.findById(id);
        Post posts = new Post();

        optionalPosts.ifPresent(value -> posts.setNameOfPost(value.getNameOfPost()));
        posts.setId(id);

        return posts;
    }
    public Post getByName(String name){
        Optional<Post> optionalPosts = repository.findByNameOfPostIgnoreCase(name);
        Post posts = new Post();
        optionalPosts.ifPresent(value -> posts.setNameOfPost(value.getNameOfPost()));
        return posts;
    }
    public Post savePost(Post posts){
        repository.save(posts);
        return  this.getById(posts.getId());
    }
}
