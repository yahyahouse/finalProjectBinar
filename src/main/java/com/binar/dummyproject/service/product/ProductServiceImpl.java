package com.binar.dummyproject.service.product;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductImage;
import com.binar.dummyproject.model.users.Users;
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

    /*
    Service untuk Product
     */
    @Override
    public void saveProduct(String productName, String productDescription, Integer productPrice, String productCategory,
                            String productStatus, Integer userId, Long productId) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        product.setProductCategory(productCategory);
        product.setProductStatus(productStatus);
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
    public void updateProduct(Long productId, String productName, String productDescription, Integer productPrice, String productCategory,
                              String productStatus) {
        productRepository.updateProduct(productName, productDescription, productPrice, productCategory, productStatus, productId);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductProp() {
        return productRepository.getAllProductProp();
    }

    @Override
    public List<Product> getProductByProductId(Long productId){
        return productRepository.findProductByProductId(productId);
    }

    @Override
    public List<Product> getProductByUserId(Integer userId) {
        return productRepository.findProductByUserId(userId);
    }

    @Override
    public Page<Product> getAllProductPageByProductNameAndProductCategory(String productName, String productCategory, Pageable pageable) {
        if (productName == null && productCategory == null){
            return productRepository.findAll(pageable);
        } else if (productName == null) {
            return productRepository.findByProductCategoryContaining(productCategory, pageable);
        } else if (productCategory == null) {
            return productRepository.findByProductNameContaining(productName, pageable);
        } else {
            return productRepository.findByProductNameContainingAndProductCategoryContaining(productName, productCategory, pageable);
        }

    }

    @Override
    public List<Product> getProductDetailByid(Long productId) {
        return productRepository.getDetailProductById(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public void updateStatusProductSold(Long productId, Integer userId) {
        productRepository.updateProductStatusSold(productId, userId);
    }


    /*
    Service untuk product image
     */
    @Override
    public void saveProdductImage(Long productId, String productImageName, String url) {
        ProductImage productImage = new ProductImage();
        productImage.setProductImageName(productImageName);
        productImage.setUrl(url);
        Product product = new Product();
        product.setProductId(productId);
        productImage.setProductId(product);
        productImageRepository.save(productImage);
    }

    @Override
    public Optional<ProductImage> deleteProductImage(Long id) {
        Optional<ProductImage> delImage = productImageRepository.findById(id);
        productImageRepository.deleteProductImage(id);
        return delImage;
    }

    @Override
    public List<ProductImage> findAllData() {
        return productImageRepository.findAll();
    }
}
