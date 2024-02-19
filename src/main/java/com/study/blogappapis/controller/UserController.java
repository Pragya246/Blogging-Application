package com.study.blogappapis.controller;

import com.study.blogappapis.models.User;
import com.study.blogappapis.payloads.ApiResponse;
import com.study.blogappapis.payloads.UserDto;
import com.study.blogappapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

//    Post-create user
    @PostMapping("/adduser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto= userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
//    Put-update user
    @PutMapping("/updateuser/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId) {
        UserDto updatedUser=userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }
//    delete-delete user
    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
        userService.deleteUser(uid);
        return new ResponseEntity(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
    }
//    get-getall users
    @GetMapping("/getusers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

//    get-getUserById
    @GetMapping("/getuser/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
