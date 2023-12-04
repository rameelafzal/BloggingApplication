package com.example.demo.controllers;


import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        Post post=this.postService.createPost(postDto, userId, categoryId);
        return ResponseEntity.created(URI.create("/posts/"+post.getPostId())).body(postDto);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id) {
        PostDto postDto = this.postService.getPost(id);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }


    @GetMapping("posts/getPostByUser")
    public ResponseEntity<List<PostDto>> getPostByUser(@RequestBody Integer userId) {
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return ResponseEntity.ok(postDtos);
    }
    @CrossOrigin
    @GetMapping("posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue="0",required = false) Integer pageNumber,
            @RequestParam(value = "sortBy",defaultValue= "postId",required = false) String sortBy
            ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,sortBy);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("posts/deletePost")
    public ResponseEntity<PostDto> deletePost(@RequestBody Integer postId) {
        this.postService.deletePost(postId);
        return null;
    }

    @PutMapping("posts/updatePost/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        this.postService.updatePost(postDto, postId);
        return null;
    }


    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDto> >searchPostByTitle(
            @PathVariable("keywords") String keywords
    ){
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return ResponseEntity.ok(postDtos);
    }

}
