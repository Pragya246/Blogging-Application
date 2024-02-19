package com.study.blogappapis.services.impl;

import com.study.blogappapis.exceptions.ResourceNotFoundException;
import com.study.blogappapis.models.User;
import com.study.blogappapis.payloads.UserDto;
import com.study.blogappapis.repository.UserRepo;
import com.study.blogappapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.DtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(userDto.getName(),"id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.UserToDto(updatedUser);
        return userDto1;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        return this.UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public User DtoToUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        return user;
    }
    public UserDto UserToDto(User user) {
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
