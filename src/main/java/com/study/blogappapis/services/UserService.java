package com.study.blogappapis.services;

import com.study.blogappapis.payloads.UserDto;


import java.util.List;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    void deleteUser(Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
}
