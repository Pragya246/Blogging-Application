package com.study.blogappapis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 4,message = "Title should be atleast 4")
    private String categoryTitle;
    private String categoryDescription;
}
