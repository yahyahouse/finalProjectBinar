package com.binar.dummyproject.service;

import com.binar.dummyproject.enumeration.ECategory;
import com.binar.dummyproject.model.Product;
import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(String productName, String productDescription, Integer productPrice, ECategory category, String productImage, Integer userId) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        product.setCategory(category);
        product.setProductImage(productImage);
        Users users = new Users();
        users.setUserId(userId);
        product.setUserId(users);
        productRepository.save(product);
    }

    @Override
    public Optional<Product> deleteProductById(Long id) {
        Optional<Product> delProduct = productRepository.findById(id);
        productRepository.deleteProductById(id);
        return  delProduct;
    }

    @Override
    public void updateProduct(Long productId, String productName, String productDescription, Integer productPrice, ECategory category, String productImage) {
        productRepository.updateProduct(productName, productImage, productDescription, productPrice, category, productId);
    }


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByUsername(String username) {
        return productRepository.findProductByUsername(username);
    }

    @Override
    public Page<Product> getAllProductPage(String productName, Pageable pageable) {
        if (productName == null)
            return productRepository.findAll(pageable);
        else
            return productRepository.findByProductName(productName, pageable);
    }

    @Override
    public List<Product> getProductByProductId(Long productId){
        return productRepository.findProductByProductId(productId);
    }
}
