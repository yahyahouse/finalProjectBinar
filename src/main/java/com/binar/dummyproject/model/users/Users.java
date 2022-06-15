package com.binar.dummyproject.model.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
