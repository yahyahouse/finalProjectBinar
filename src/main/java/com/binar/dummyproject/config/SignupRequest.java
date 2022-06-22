package com.binar.dummyproject.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Setter
@Getter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 6, max = 50)
    private String address;

    private String usersImage;

    @NotBlank
    @Size(min = 9, max = 13)
    private String phone;

    @NotBlank
    private String city;

}
