package com.binar.dummyproject.service.product;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductImage;
import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.repository.product.ProductImageRepository;
import com.binar.dummyproject.repository.product.ProductRepository;
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

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public void saveProduct(String productName, String productDescription, Integer productPrice, String productCategory,
                            Integer userId, Long productId) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        product.setProductCategory(productCategory);
        Users users = new Users();
        users.setUserId(userId);
        product.setUserId(users);
        productRepository.save(product);
    }

    @Override
    public void saveProdductImage(Long productId, String productImageName, byte[] productImageFile) {
        ProductImage productImage = new ProductImage();
        productImage.setProductImageName(productImageName);
        productImage.setProductImageFile(productImageFile);
        Product product = new Product();
        product.setProductId(productId);
        productImage.setProductId(product);
        productImageRepository.save(productImage);
    }


    @Override
    public Optional<Product> deleteProductById(Long id) {
        Optional<Product> delProduct = productRepository.findById(id);
        productRepository.deleteProductById(id);
        return  delProduct;
    }

    @Override
    public void updateProduct(Long productId, String productName, String productDescription, Integer productPrice, String productCategory, String productImage) {
        productRepository.updateProduct(productName, productImage, productDescription, productPrice, productCategory, productId);
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
