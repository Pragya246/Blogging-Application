package com.study.blogappapis.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String user, id;
    Integer userId;
    public ResourceNotFoundException(String user, String id, Integer userId) {
        super(String.format("%s not found with %s : %s",user,id,userId));
        this.user=user;
        this.id=id;
        this.userId=userId;
    }
}
