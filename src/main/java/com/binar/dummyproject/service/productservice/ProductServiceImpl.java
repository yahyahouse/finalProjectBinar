package com.binar.dummyproject.service.productservice;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.repository.productrepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(String productName, String productCategory, String productDesc) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductCategory(productCategory);
        product.setProductDesc(productDesc);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteProductByProductId(productId);
    }


}
