package com.study.blogappapis.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 3, message = "Name should be of minimum 3 characters.")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Should be atleast 8 chars, Contains at least one digit, Contains at least one lower alpha char and one upper alpha char, Does not contain space.")
    private String password;
    private String about;


    
}
