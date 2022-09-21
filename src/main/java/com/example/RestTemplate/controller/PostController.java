package com.example.RestTemplate.controller;

import com.example.RestTemplate.model.Post;
import com.example.RestTemplate.response.PostResponse;
import com.example.RestTemplate.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class PostController {

    @Autowired
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> createPosts(@RequestBody Post post){
        PostResponse postResponse=postService.createPosts(post);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPosts(@PathVariable Long id){
        Post post=postService.getPosts(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{id}",produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable  Long  id){
        postService.deleteById(id);
        log.info("successfully deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping(path ="/posts/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post){
        post=postService.updatePosts();
        return new ResponseEntity<>(post,HttpStatus.CREATED);
    }
}
