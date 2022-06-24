package com.binar.dummyproject.controller;

import com.binar.dummyproject.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Users", description = "API for processing various operations with Users entity")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Operation(summary = "Update users profile")
    @PutMapping("public/update-users-profile")
    public ResponseEntity<Map<String, Object>> updateUsersProfile(
            @Schema(example = "{" +
                    "\"userId\":\"22\"," +
                    "\"username\":\"sellerR\"," +
                    "\"address\":\"Jl. Mermaidman\"," +
                    "\"usersImage\":\"https://freeimage.host/i/hiui1p\"," +
                    "\"city\":\"Ambon\"," +
                    "\"phone\":\"0877777773\"," +
                    "\"role\":[\"SELLER\"]" +
                    "}")
            @RequestBody Map<String, Object> usersProfile){
        usersService.updateUsersProfile(Integer.valueOf(usersProfile.get("userId").toString()), usersProfile.get("username").toString(),
                usersProfile.get("address").toString(), usersProfile.get("phone").toString(), usersProfile.get("city").toString(),
                usersProfile.get("usersImage").toString());

        Map<String,Object> response = new HashMap<>();
        response.put("userId", usersProfile.get("userId"));
        response.put("username", usersProfile.get("username"));
        response.put("address", usersProfile.get("address"));
        response.put("phone", usersProfile.get("phone"));
        response.put("city", usersProfile.get("city"));
        response.put("usersImage", usersProfile.get("usersImage"));
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "users change password")
    @PutMapping("public/update-users-password")
    public ResponseEntity <Map<String, Object>> updateUsersPassword(
            @RequestBody Map<String, Object> usersPassword){
        usersService.updateUsersPassword(usersPassword.get("password").toString(), Integer.valueOf(usersPassword.get("userId").toString()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
