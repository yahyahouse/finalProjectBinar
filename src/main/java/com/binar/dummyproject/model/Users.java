package com.binar.dummyproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "address")
    private String address;

    @Column(name = "noHp")
    private String noHp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public Users() {

    }

    public Users(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Users(String username, String email, String password, String address, String noHp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.noHp = noHp;
    }
}
