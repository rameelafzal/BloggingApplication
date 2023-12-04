package com.example.demo.services.impl;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Post createPost(PostDto postDto,int userId,int categoryId) {
        User user =this.userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","User Id",userId)));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("user","User id",categoryId));
        Post post=this.toPost(postDto);
        post.setAddDate(LocalDate.now());
        post.setCategory(category);
        post.setUser(user);
        post=this.postRepo.save(post);
        return post;
    }

    @Override

    public Post updatePost(PostDto postDto, Integer id) {

        Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post=this.postRepo.save(post);
        return post;
    }

    @Override
    public int deletePost(Integer id) {
        Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        this.postRepo.delete(post);
        return 0;
    }

    @Override
    public PostDto getPost(Integer id) {
        Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return  this.toPostDto(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,String sortBy) {
        int pageSize=4;
        Pageable pageable=  PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Post> pagePost=this.postRepo.findAll(pageable);
        List<Post> postList=pagePost.getContent();
        List<PostDto> postDtoList = postList.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setLastPage(pagePost.isLast());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        return  postResponse;

    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> postList=this.postRepo.findByCategory(category);
        return postList.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user",userId));
        List<Post> postList=this.postRepo.findByUser(user);
        return postList.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> postList=this.postRepo.findByTitleContaining(keyword);
        return postList.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public Post toPost(PostDto postDto){
        return this.modelMapper.map(postDto,Post.class);
    }
    public PostDto toPostDto(Post post){
        return this.modelMapper.map(post,PostDto.class);
    }
}
