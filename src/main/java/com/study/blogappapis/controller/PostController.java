package com.study.blogappapis.controller;

import com.study.blogappapis.payloads.ApiResponse;
import com.study.blogappapis.payloads.PostDto;
import com.study.blogappapis.payloads.PostResponse;
import com.study.blogappapis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

//    post: create-post
    @PostMapping("/create-post/userid{userId}/categoryid{categoryId}")
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId) {
        PostDto createdPost=postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

//    put: update-post
    @PutMapping("/update/{postId}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost=postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

//    delete: deletepost
    @DeleteMapping("deletePostById/{postId}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("post deleted successfully",true));
    }

//    get: get-post{id}
    @GetMapping("/getpostByPostId{postId}")
    ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId) {
        PostDto getPostbyPid=postService.getPostByPostId(postId);
        return ResponseEntity.ok(getPostbyPid);
    }

//    get: get-allpost
    @GetMapping("/getAllPosts")
    ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageno", defaultValue = "0", required = false) Integer pageno,
                                             @RequestParam(value = "pagesize", defaultValue = "5", required = false) Integer pagesize,
                                             @RequestParam(value = "sortBy" , defaultValue = "title" , required = false) String sortBy,
                                             @RequestParam(value = "sortDir" , defaultValue = "asc" , required = false) String sortDir) {
        PostResponse postResponse=postService.getAllPost(pageno,pagesize,sortBy,sortDir);
        return ResponseEntity.ok(postResponse);
    }

//    get: get-post{userid}
    @GetMapping("getPostsByuserid/{userId}")
    ResponseEntity<PostResponse> getAllPostByUserId(@PathVariable Integer userId,
                                                    @RequestParam(value = "pageno", defaultValue = "0", required = false) Integer pageno,
                                                    @RequestParam(value = "pagesize", defaultValue = "5", required = false) Integer pagesize,
                                                    @RequestParam(value = "sortBy" , defaultValue = "title" , required = false) String sortBy,
                                                    @RequestParam(value = "sortDir" , defaultValue = "asc" , required = false) String sortDir) {
        PostResponse postResponse=postService.getPostByUserId(userId,pageno,pagesize,sortBy,sortDir);
        return ResponseEntity.ok(postResponse);
    }

//    get: get-post{categoryid}
    @GetMapping("getPostsByCategoryid/{categoryId}")
    ResponseEntity<List<PostDto>> getAllPostBycategoryId(@PathVariable Integer categoryId) {
        List<PostDto> postDtos=postService.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(postDtos);
    }
//    get: search post{keyword}
    @GetMapping("searchPostByKeyword/{keywords}")
    ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords) {
        List<PostDto> postDtos=postService.searchPosts(keywords);
        return ResponseEntity.ok(postDtos);
    }
}
