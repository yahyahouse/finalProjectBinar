package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductDetailResponse;
import com.binar.dummyproject.model.product.ProductResponse;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Home", description = "API for access homepage")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/home-page")
public class HomeController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Get detail product no auth")
    @GetMapping(value = "/get-detail-product/{productId}")
    public ResponseEntity<ProductDetailResponse> getDetailProductById(@PathVariable("productId") Long productId) {
        Product products = productService.getProductDetailByid(productId);
        return new ResponseEntity(new ProductDetailResponse(products), HttpStatus.OK);
    }

    @Operation(summary = "Show all products sort and filter")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProductPage(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productCategory,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "Available") String productStatus
    ) {
        try {
            Pageable paging = PageRequest.of(page - 1, size, Sort.by("productPrice"));

            Page<Product> productPage = productService.getAllProductPageByProductNameAndProductCategoryAndProductStatus(productName, productStatus, productCategory, paging);
            List<Product> products = productPage.getContent();
            Map<String, Object> response = new HashMap<>();
            List<ProductResponse> productResponse =
                    products.stream().map(ProductResponse::new).collect(
                            Collectors.toList());
            response.put("products", productResponse);
            response.put("currentPage", productPage.getNumber() + 1);
            response.put("totalProducts", productPage.getTotalElements());
            response.put("totalPages", productPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}