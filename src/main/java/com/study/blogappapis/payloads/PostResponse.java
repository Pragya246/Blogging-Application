package com.study.blogappapis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageno;
    private int pagesize;
    private String sortby;
    private long totalelements;
    private int totalpages;
    private boolean islast;
}
