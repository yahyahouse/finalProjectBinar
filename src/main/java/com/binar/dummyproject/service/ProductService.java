package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    void saveProduct (String productName, String productDescription, Integer productPrice, String address, String productImage, Integer userId);
    Optional<Product> deleteProductById(Long productId);
    void updateProduct (Long productId, String productName, String productDescription, Integer productPrice, String address, String productImage);
    List<Product> getAllProduct();
    List<Product> getProductByUsername (String username);
}