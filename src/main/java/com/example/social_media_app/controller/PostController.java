package com.example.social_media_app.controller;

import com.example.social_media_app.entity.Post;
import com.example.social_media_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
}
