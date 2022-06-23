package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    void saveProduct (String productName, String productDescription, Integer productPrice, String address, String productImage, Integer userId);
    Optional<Product> deleteProductById(Long productId);
    void updateProduct (Long productId, String productName, String productDescription, Integer productPrice, String address, String productImage);
    List<Product> getAllProduct();
    List<Product> getProductByProductId(Long productId);
    List<Product> getProductByUsername (String username);
    Page<Product> getAllProductPage(String productName, Pageable pageable);
}