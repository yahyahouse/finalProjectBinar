package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.Product;
import com.binar.dummyproject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Home", description = "API for access homepage")
@RestController
@RequestMapping("/home-page")
public class HomeController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Show all products by seller")
    @GetMapping("/show-products")
    public ResponseEntity<List<Product>> allProduct(){
        return ResponseEntity.accepted().body(productService.getAllProduct());
    }
}
