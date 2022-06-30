package com.binar.dummyproject.controller;

import com.binar.dummyproject.service.users.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Users", description = "API for processing various operations with Users entity")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Operation(summary = "users change password")
    @PutMapping("public/update-users-password")
    public ResponseEntity <Map<String, Object>> updateUsersPassword(
            @RequestBody Map<String, Object> usersPassword){
        usersService.updateUsersPassword(usersPassword.get("password").toString(), Integer.valueOf(usersPassword.get("userId").toString()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
