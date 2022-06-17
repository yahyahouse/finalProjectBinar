package com.binar.dummyproject.service;

import com.binar.dummyproject.model.Product;
import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(String nama, String deskripsi, Integer price, String address, String image, Integer userId) {
        Product product = new Product();
        product.setNama(nama);
        product.setDeskripsi(deskripsi);
        product.setPrice(price);
        product.setAddress(address);
        product.setImage(image);
        Users users = new Users();
        users.setUserId(userId);
        product.setUserId(users);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public void updateProduct(long id, String nama, String deskripsi, Integer price, String address, String image) {
        productRepository.updateProduct(nama, image, deskripsi, price, address, id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByUsername(String username) {
        return productRepository.findProductByUsername(username);
    }

}
