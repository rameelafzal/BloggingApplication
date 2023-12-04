package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;

import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto,int userId,int categoryId);
    Post updatePost(PostDto postDto,Integer id);
    int deletePost(Integer id);
    PostDto getPost(Integer id);
    PostResponse getAllPost(Integer pageNumber,String sortBy);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);

}
