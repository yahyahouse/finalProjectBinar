package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.UploadResponse;
import com.binar.dummyproject.model.product.ProductResponse;
import com.binar.dummyproject.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home", description = "API for access homepage")
@RestController
@RequestMapping("/home-page")
public class HomeController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Show all products")
    @GetMapping("/show-products")
    public ResponseEntity<ProductResponse> allProduct(){
        return new ResponseEntity(productService.getAllProduct(), HttpStatus.OK);
    }
}
