package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.Product;
import com.binar.dummyproject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Product", description = "API for processing various operations with Product entity")
@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService productService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New product added",
            content = {@Content(
                    mediaType = "application/json",
                    schema =@Schema(example = "{" +
                            "\"productName\":\"Jam tangan\"," +
                            "\"productDescription\":\"Ini untuk melihat waktu\"," +
                            "\"productPrice\":\"250000\"," +
                            "\"address\":\"Jl. Rumah\"," +
                            "\"productImage\":\"0\"," +
                            "\"userId\":\"1\"" +
                            "}")
            )})
    })
    @Operation(summary = "Add a new product by seller")
    @PostMapping("/seller/add-product")
    public ResponseEntity<Map<String, Object>> addProduct (
            @Schema(example = "{" +
                    "\"productName\":\"Jam tangan\"," +
                    "\"productDescription\":\"Ini untuk melihat waktu\"," +
                    "\"productPrice\":\"250000\"," +
                    "\"address\":\"Jl. Rumah\"," +
                    "\"productImage\":\"0\"," +
                    "\"userId\":\"1\"" +
                    "}")
            @RequestBody Map<String, Object> product){
    productService.saveProduct(product.get("productName").toString(), product.get("productDescription").toString(), Integer.valueOf(product.get("productPrice").toString()),
            product.get("address").toString(), product.get("productImage").toString(), Integer.valueOf(product.get("userId").toString()));

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("productName", product.get("productName"));
    responseBody.put("productDescription", product.get("productDescription"));
    responseBody.put("productPrice", product.get("productPrice"));
    responseBody.put("address", product.get("address"));
    responseBody.put("productImage", product.get("productImage"));
    responseBody.put("userId", product.get("userId"));

    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.put("dummyProject", Arrays.asList("halo"));
    return ResponseEntity.ok()
                .header("dummyProject", "Test")
                .body(responseBody);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get all product",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{" +
                            "\"productName\":\"Jam tangan\"," +
                            "\"productDescription\":\"Ini untuk melihat waktu\"," +
                            "\"productPrice\":\"250000\"," +
                            "\"address\":\"Jl. Rumah\"," +
                            "\"productImage\":\"0\"" +
                            "}")
            )})
    })
    @Operation(summary = "Show all products by seller")
    @GetMapping("/seller/show-products")
    public ResponseEntity<List<Product>> allProduct(){
        return ResponseEntity.accepted().body(productService.getAllProduct());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Product updated",
                    content = {@Content(
                            mediaType = "application/json",
                            schema =@Schema(example = "{" +
                                    "\"productId\":\"1\"," +
                                    "\"productName\":\"Jam tangan\"," +
                                    "\"productDescription\":\"Ini untuk melihat waktu\"," +
                                    "\"productPrice\":\"250000\"," +
                                    "\"address\":\"Jl. Rumah\"," +
                                    "\"productImage\":\"0\"," +
                                    "\"userId\":\"1\"" +
                                    "}")
                    )})
    })
    @Operation(summary = "Update existing product by seller")
    @PutMapping("/seller/update-product")
    public ResponseEntity<Map<String, Object>> updateProduct (
            @Schema(example = "{" +
                    "\"productId\":\"1\"," +
                    "\"productName\":\"Jam tangan\"," +
                    "\"productDescription\":\"Ini untuk melihat waktu\"," +
                    "\"productPrice\":\"250000\"," +
                    "\"address\":\"Jl. Rumah\"," +
                    "\"productImage\":\"0\"" +
                    "}")
            @RequestBody Map<String, Object> product){

        productService.updateProduct(Long.valueOf(product.get("productId").toString()), product.get("productName").toString(), product.get("productDescription").toString(),
                Integer.valueOf(product.get("productPrice").toString()), product.get("address").toString(), product.get("productImage").toString());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("productId", product.get("productId"));
        responseBody.put("productName", product.get("productName"));
        responseBody.put("productDescription", product.get("productDescription"));
        responseBody.put("productPrice", product.get("productPrice"));
        responseBody.put("address", product.get("address"));
        responseBody.put("productImage", product.get("productImage"));

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("dummyProject", Arrays.asList("halo"));
        return ResponseEntity.ok().body(responseBody);
    }

    @ApiResponses(value = {
            @ApiResponse( content = {
                    @Content(examples = {})
            }, responseCode = "204", description = "Success deleted a product")
    })
    @Operation(summary = "Delete a product")
    @DeleteMapping("/seller/delete-product/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProductById(
            @Parameter(description = "add id to delete the product item")
            @PathVariable("productId") Long productId){
        Optional<Product> product = productService.deleteProductById(productId);

        Map<String, Object> response = new HashMap<>();
        if(product.isPresent()){
            response.put("success", true);
            response.put("deletedData", product);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(examples = {})
            }, responseCode = "202", description = "Success show products")
    })
    @Operation(summary = "Get product by seller username")
    @GetMapping(value = "/seller/get-product-seller/{username}")
    public ResponseEntity<List<Product>> getProductByUserId(@PathVariable("username") String username){
        productService.getProductByUsername(username);
        return ResponseEntity.accepted().body(productService.getProductByUsername(username));
    }

    @GetMapping("/buyer/get-product-sortedBy-productName")
    public ResponseEntity<Map<String, Object>> getAllProductPage(
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size, Sort.by("productName"));

            Page<Product> productPage = productService.getAllProductPage(productName, paging);
            List<Product> products = productPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("products", products);
            response.put("currentPage", productPage.getNumber());
            response.put("totalProducts", productPage.getTotalElements());
            response.put("totalPages", productPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
