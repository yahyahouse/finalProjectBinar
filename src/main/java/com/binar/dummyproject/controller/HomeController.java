package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Home", description = "API for access homepage")
@RestController
@RequestMapping("/home-page")
public class HomeController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Show all products")
    @GetMapping("/show-products")
    public ResponseEntity<Map<String, Object>> allProduct(){
        List<Map<String, Object>> result = new ArrayList<>();
        List<Product> product = productService.getAllProduct();
        Map<String, Object> response = new HashMap<>();
        response.put("data", product);
        result.add(response);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/get-product-page")
    public ResponseEntity<Map<String, Object>> getAllProductPage(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productCategory,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page - 1, size, Sort.by("productPrice"));

            Page<Product> productPage = productService.getAllProductPageByProductNameAndProductCategory(productName, productCategory, paging);
            List<Product> products = productPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("products", products);
            response.put("currentPage", productPage.getNumber() + 1);
            response.put("totalProducts", productPage.getTotalElements());
            response.put("totalPages", productPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}