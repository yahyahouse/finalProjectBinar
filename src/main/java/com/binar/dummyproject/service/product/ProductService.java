package com.binar.dummyproject.service.product;

import com.binar.dummyproject.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void saveProduct(String productName, String productDescription, Integer productPrice, String productCategory, Integer userId, Long productId);
    void saveProdductImage(Long productId, String productImageName, byte[] productImageFile);
    Optional<Product> deleteProductById(Long productId);
    void updateProduct (Long productId, String productName, String productDescription, Integer productPrice, String productCategory, String productImage);
    List<Product> getAllProduct();
    List<Product> getProductByProductId(Long productId);
    List<Product> getProductByUsername (String username);
    Page<Product> getAllProductPage(String productName, Pageable pageable);
}