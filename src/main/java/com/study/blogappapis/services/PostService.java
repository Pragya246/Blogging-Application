package com.study.blogappapis.services;

import com.study.blogappapis.payloads.PostDto;
import com.study.blogappapis.payloads.PostResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {



    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getPostByUserId(Integer userId, Integer pageno, Integer pagesize, String sortBy, String sortDir);
    List<PostDto> getPostByCategoryId(Integer categoryId);
    PostResponse getAllPost(Integer pageno, Integer pagesize, String sortBy, String sortDir);
    PostDto getPostByPostId(Integer postId);
    List<PostDto> searchPosts(String keywords);


}
