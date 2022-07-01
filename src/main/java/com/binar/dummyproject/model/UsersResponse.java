package com.binar.dummyproject.model;

import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {
    private Integer userId;
    private String username;
    private String[] url;
    private String address;
    private String city;
    private String phone;

    public UsersResponse (Integer userId, String username, String[] url, String address, String city, String phone) {
        this.userId = userId;
        this.username = username;
        this.url = url;
        this.address = address;
        this.city = city;
        this.phone = phone;

    }
}

