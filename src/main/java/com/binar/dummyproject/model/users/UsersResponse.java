package com.binar.dummyproject.model.users;

import lombok.Data;

@Data
public class UsersResponse {

    private Integer userId;
    private String username;
    private String address;
    private String city;
    private String phone;
    private String[] url;

    public UsersResponse(Integer userId, String username, String address, String city,
                         String phone, String[] url) {
        this.userId = userId;
        this.username = username;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.url = url;
    }

}
