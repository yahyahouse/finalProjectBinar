package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void saveProduct (String nama, String deskripsi, Integer price, String address, String image, Integer userId);
    Optional<Product> deleteProductById(Long id);
    void updateProduct (long id, String nama, String deskripsi, Integer price, String address, String image);
    List<Product> getAllProduct();
    List<Product> getProductByUsername (String username);
}