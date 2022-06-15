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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Product", description = "API for processing various operations with Product entity")
@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService productService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New product added",
            content = {@Content(
                    mediaType = "application/json",
                    schema =@Schema(example = "{" +
                            "\"nama\":\"Jam tangan\"," +
                            "\"deskripsi\":\"Ini untuk melihat waktu\"," +
                            "\"price\":\"250000\"," +
                            "\"address\":\"Jl. Rumah\"," +
                            "\"image\":\"0\"" +
                            "}")
            )})
    })
    @Operation(summary = "Add a new product by seller")
    @PostMapping("/seller/add-product")
    public ResponseEntity<Map<String, Object>> addProduct (
            @Schema(example = "{" +
                    "\"nama\":\"Jam tangan\"," +
                    "\"deskripsi\":\"Ini untuk melihat waktu\"," +
                    "\"price\":\"250000\"," +
                    "\"address\":\"Jl. Rumah\"," +
                    "\"image\":\"0\"" +
                    "}")
            @RequestBody Map<String, Object> product){

    productService.saveProduct(product.get("nama").toString(), product.get("deskripsi").toString(), Integer.valueOf(product.get("price").toString()),
            product.get("adress").toString(), product.get("image").toString());

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("nama", product.get("nama"));
    responseBody.put("deskripsi", product.get("deskripsi"));
    responseBody.put("price", product.get("price"));
    responseBody.put("adress", product.get("adress"));
    responseBody.put("image", product.get("image"));

    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.put("dummyProject", Arrays.asList("halo"));
    return ResponseEntity.ok().body(responseBody);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Get all product",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(example = "{" +
                            "\"nama\":\"Jam tangan\"," +
                            "\"deskripsi\":\"Ini untuk melihat waktu\"," +
                            "\"price\":\"250000\"," +
                            "\"address\":\"Jl. Rumah\"," +
                            "\"image\":\"0\"" +
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
                                    "\"id\":\"1\"," +
                                    "\"nama\":\"Jam tangan\"," +
                                    "\"deskripsi\":\"Ini untuk melihat waktu\"," +
                                    "\"price\":\"250000\"," +
                                    "\"address\":\"Jl. Rumah\"," +
                                    "\"image\":\"0\"" +
                                    "}")
                    )})
    })
    @Operation(summary = "Update existing product by seller")
    @PutMapping("/seller/update-product")
    public ResponseEntity<Map<String, Object>> updateProduct (
            @Schema(example = "{" +
                    "\"id\":\"1\"," +
                    "\"nama\":\"Jam tangan\"," +
                    "\"deskripsi\":\"Ini untuk melihat waktu\"," +
                    "\"price\":\"250000\"," +
                    "\"address\":\"Jl. Rumah\"," +
                    "\"image\":\"0\"" +
                    "}")
            @RequestBody Map<String, Object> product){

        productService.saveProduct(product.get("nama").toString(), product.get("deskripsi").toString(), Integer.valueOf(product.get("price").toString()),
                product.get("adress").toString(), product.get("image").toString());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", product.get("id"));
        responseBody.put("nama", product.get("nama"));
        responseBody.put("deskripsi", product.get("deskripsi"));
        responseBody.put("price", product.get("price"));
        responseBody.put("adress", product.get("adress"));
        responseBody.put("image", product.get("image"));

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
    @DeleteMapping("/seller/delete-product/{id}")
    public ResponseEntity<String> deleteProductById(
            @Parameter(description = "add id to delete the product item")
            @PathVariable("id") long id){
        productService.deleteProduct(id);
        return ResponseEntity.accepted().body("Deleted product "+id+" success");
    }
}
