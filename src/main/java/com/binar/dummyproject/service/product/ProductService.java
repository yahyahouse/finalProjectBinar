package com.binar.dummyproject.service.product;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    /*
    Service untuk Product
     */
    void saveProduct(String productName, String productDescription, Integer productPrice, String productCategory, Integer userId, Long productId);
    Optional<Product> deleteProductById(Long productId);
    void updateProduct (Long productId, String productName, String productDescription, Integer productPrice, String productCategory);
    List<Product> getAllProduct();
    List<Product> getAllProductProp();
    List<Product> getProductByProductId(Long productId);
    List<Product> getProductByUserId (Integer userId);
    Page<Product> getAllProductPageByProductNameAndProductCategory(String productName, String productCategory, Pageable pageable);
    List<Product> getProductDetailByid (Long productId);
    Product getProductById(Long productId);

    /*
    Service untuk productImage
     */
    void saveProdductImage(Long productId, String productImageName, String url);
    Optional<ProductImage> deleteProductImage(Long productId);
    List<ProductImage> findAllData();
}