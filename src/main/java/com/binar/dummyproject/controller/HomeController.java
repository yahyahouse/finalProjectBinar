package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductImage;
import com.binar.dummyproject.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        for(Product p : productService.getAllProductProp()){
            Map<String, Object> responses = new HashMap<>();
            responses.put("image", p.getProductImages());
            result.add(responses);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}