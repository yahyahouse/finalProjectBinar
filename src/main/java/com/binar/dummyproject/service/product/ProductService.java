package com.binar.dummyproject.service.product;

import com.binar.dummyproject.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    /*
    Service untuk Product
     */
    void saveProduct(String productName, String productDescription, Integer productPrice, String productCategory,
                     String productStatus, Integer userId, String url, String url2, String url3, String url4, LocalDateTime localDateTime);
    Optional<Product> deleteProductById(Long productId);
    void updateProduct (Long productId, String productName, String productDescription, Integer productPrice, String productCategory,
                        String productStatus, Integer userId, String url, String url2, String url3, String url4, LocalDateTime localDateTime);
    List<Product> getProductByUserId (Integer userId);
    Page<Product> getAllProductPageByProductNameAndProductCategoryAndProductStatus(String productName, String productCategory, String productStatus, Pageable pageable);
    Product getProductDetailByid (Long productId);
    Product getProductById(Long productId);
    void updateStatusProductSold (Long productId, Integer userId);

    List<Product> getProdutSold (Integer userId);
}