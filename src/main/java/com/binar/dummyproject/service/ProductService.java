package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void saveProduct (String nama, String deskripsi, Integer price, String address, String image);
    void deleteProduct (long id);
    void updateProduct (long id, String nama, String deskripsi, Integer price, String address, String image);
    List<Product> getAllProduct();
}