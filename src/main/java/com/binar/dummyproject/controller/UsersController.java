package com.binar.dummyproject.controller;

import com.binar.dummyproject.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "API for processing various operations with Users entity")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

}
