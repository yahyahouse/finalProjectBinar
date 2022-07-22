package com.binar.dummyproject.model.users;

import lombok.Data;



@Data
public class UsersResponse {
    private Integer userId;
    private String fullNameUser;
    private String[] urlUser;
    private String address;
    private String city;
    private String phone;

    public UsersResponse (Integer userId, String fullNameUser, String[] urlUser, String address, String city, String phone) {
        this.userId = userId;
        this.fullNameUser = fullNameUser;
        this.urlUser = urlUser;
        this.address = address;
        this.city = city;
        this.phone = phone;

    }
}

