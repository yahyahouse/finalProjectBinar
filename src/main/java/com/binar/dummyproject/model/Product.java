package com.binar.dummyproject.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private String image;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "price")
    private Integer price;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

}

