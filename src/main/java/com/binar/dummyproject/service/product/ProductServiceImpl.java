package com.binar.dummyproject.service.product;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.repository.product.ProductRepository;
import com.binar.dummyproject.service.notification.NotificationService;
import com.binar.dummyproject.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.binar.dummyproject.model.InfoConst.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UsersService usersService;


    /*
    Service untuk Product
     */
    @Override
    public void saveProduct(String productName, String productDescription, Integer productPrice, String productCategory,
                            String productStatus, Integer userId, String url, String url2, String url3, String url4, LocalDateTime localDateTime) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductPrice(productPrice);
        product.setProductCategory(productCategory);
        product.setProductStatus(productStatus);
        product.setUrl(url);
        product.setUrl2(url2);
        product.setUrl3(url3);
        product.setUrl4(url4);
        product.setLocalDateTime(localDateTime);
        Users users = usersService.findByUserId(userId);
        product.setUserId(users);
        productRepository.save(product);

        if(productStatus.equals("Available")){
            notificationService.saveNotification(PRODUCT_TERBIT, product, userId);
        }
    }

    @Override
    public Optional<Product> deleteProductById(Long id) {
        Optional<Product> delProduct = productRepository.findById(id);
        productRepository.deleteProductById(id);
        return delProduct;
    }

    @Override
    public void updateProduct(Long productId,String productName, String productDescription, Integer productPrice, String productCategory,
                              String productStatus, Integer userId, String url, String url2, String url3, String url4, LocalDateTime localDateTime) {
        productRepository.updateProduct(productName, productDescription, productPrice, productCategory, productStatus, productId,url, url2,url3,url4,
                localDateTime);
    }

    @Override
    public List<Product> getProductByUserId(Integer userId) {
        return productRepository.findProductByUserId(userId);
    }

    @Override
    public Page<Product> getAllProductPageByProductNameAndProductCategoryAndProductStatus(String productName, String productCategory, String productStatus, Pageable pageable) {
        if (productName == null && productCategory == null && productStatus.equals("Available")){
            return productRepository.findAll(pageable);
        } else if (productName == null && productStatus.equals("Available")) {
            return productRepository.findByProductCategoryContainingIgnoreCase(productCategory, pageable);
        } else if (productCategory == null && productStatus.equals("Available")) {
            return productRepository.findByProductNameContainingIgnoreCase(productName, pageable);
        } else {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductCategoryContainingIgnoreCaseAndProductStatus(productName, productStatus, productCategory, pageable);
        }

    }

    @Override
    public Product getProductDetailByid(Long productId) {
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

    @Override
    public List<Product> getProdutSold(Integer userId) {
        return productRepository.getProductSold(userId);
    }
}
