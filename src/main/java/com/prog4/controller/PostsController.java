package com.prog4.controller;

import com.prog4.model.Post;
import com.prog4.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class PostsController {
    private final PostsService service;

    @GetMapping("/posts")
    public String getAllPosts(Model model){
        List<Post> postsLists = service.findAll();
        model.addAttribute("posts",postsLists);
        return "posts/add-post";
    }

    @GetMapping("/save-post")
    public String savePost(Model model){
        model.addAttribute("post",new Post());
        return "posts/add-post";
    }

    @PostMapping("/save-post")
    public String addPosts(@ModelAttribute("posts") Post post){
        Post post1 = service.savePost(post);
        return "redirect:/posts/" + post1.getId();
    }
}
