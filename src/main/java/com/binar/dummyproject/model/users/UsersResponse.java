package com.binar.dummyproject.model.users;

import lombok.Data;



@Data
public class UsersResponse {
    private Integer userId;
    private String fullNameUser;
    private String[] url;
    private String address;
    private String city;
    private String phone;

    public UsersResponse (Integer userId, String fullNameUser, String[] url, String address, String city, String phone) {
        this.userId = userId;
        this.fullNameUser = fullNameUser;
        this.url = url;
        this.address = address;
        this.city = city;
        this.phone = phone;

    }
}

