package com.study.blogappapis.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int postid;
    private String title;
    private String content;
    private String imageName;
    private Date imageDate;
    private UserDto user;
    private CategoryDto category;
}
