package com.binar.dummyproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "price")
    private Integer price;

    @Column(name = "address")
    private String address;

}
