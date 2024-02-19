package com.study.blogappapis.services.impl;

import com.study.blogappapis.exceptions.ResourceNotFoundException;
import com.study.blogappapis.models.Category;
import com.study.blogappapis.models.Post;
import com.study.blogappapis.models.User;
import com.study.blogappapis.payloads.PostDto;
import com.study.blogappapis.payloads.PostResponse;
import com.study.blogappapis.repository.CategoryRepo;
import com.study.blogappapis.repository.PostRepo;
import com.study.blogappapis.repository.UserRepo;
import com.study.blogappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
        Category category=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        Post post=modelMapper.map(postDto,Post.class);
        post.setImageDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost=this.postRepo.save(post);
        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageDate(new Date());
        Post savedPost=this.postRepo.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostByPostId(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));
        return modelMapper.map(post,PostDto.class);
    }

//Page<Post> findByUser (User user, Pageable p);
//
//Page<Post> findByCategory (Category category, Pageable p);

    @Override
    public List<PostDto> getPostByCategoryId(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        List<Post> posts=postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostResponse getAllPost(Integer pageno, Integer pagesize, String sortBy, String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort=Sort.by(sortBy).ascending();
        else
            sort=Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageno, pagesize, sort);
        Page<Post> postPage=postRepo.findAll(pageable);
        List<Post> posts=postPage.getContent();
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageno(postPage.getNumber());
        postResponse.setPagesize(postPage.getSize());
        postResponse.setTotalpages(postPage.getTotalPages());
        postResponse.setIslast(postPage.isLast());
        return postResponse;
    }
    @Override
    public PostResponse getPostByUserId(Integer userId, Integer pageno, Integer pagesize, String sortBy, String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort=Sort.by(sortBy).ascending();
        else
            sort=Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageno, pagesize, sort);
        User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","userid",userId));
        Page<Post> postPage=postRepo.findByUser(user,pageable);
        List<Post> posts=postPage.getContent();
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageno(postPage.getNumber());
        postResponse.setPagesize(postPage.getSize());
        postResponse.setTotalpages(postPage.getTotalPages());
        postResponse.setIslast(postPage.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
        List<Post> posts=postRepo.searchByTitle("%"+keywords+"%");
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}
