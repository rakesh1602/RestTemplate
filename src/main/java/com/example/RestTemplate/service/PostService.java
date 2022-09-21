package com.example.RestTemplate.service;

import com.example.RestTemplate.model.Post;
import com.example.RestTemplate.response.PostResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class PostService {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${base.url}")
    private String fakeUrl;

    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PostResponse createPosts(Post post) {
        HttpEntity<Post> http = new HttpEntity<>(post);
        ResponseEntity<PostResponse> postResponseResponseEntity = restTemplate.exchange(fakeUrl + "/posts", HttpMethod.POST, http, PostResponse.class);
        return postResponseResponseEntity.getBody();

    }

    public Post getPosts(Long id) {
        Post post = restTemplate.getForObject(fakeUrl + "/posts/{id}", Post.class, id);
        return post;
    }

    public void deleteById(Long id) {
        restTemplate.delete(fakeUrl + "/posts/{id}", id);
    }

    public Post updatePosts() {
        Post post=new Post();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Post> entity=new HttpEntity<>(post, httpHeaders);
        return restTemplate.exchange(fakeUrl + "/posts/{id}", HttpMethod.PUT,entity,Post.class).getBody();
    }
}
