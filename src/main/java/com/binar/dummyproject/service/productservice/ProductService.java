package com.binar.dummyproject.service.productservice;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    void saveProduct (String productName, String productCategory, String productDesc);
    void deleteProduct (Integer productId);
}
