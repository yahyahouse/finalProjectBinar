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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Product", description = "API for processing various operations with Product entity")
@RestController
@RequestMapping("/product")
public class ProductController{

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New product added",
            content = {@Content(
                    mediaType = "application/json",
                    schema =@Schema(example = "{" +
                            "\"nama\":\"Jam tangan\"," +
                            "\"deskripsi\":\"Ini untuk melihat waktu\"," +
                            "\"price\":\"250000\"," +
                            "\"address\":\"Jl. Rumah\"," +
                            "\"image\":\"0\"," +
                            "\"userId\":\"1\"" +
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
                    "\"image\":\"0\"," +
                    "\"userId\":\"1\"" +
                    "}")
            @RequestBody Map<String, Object> product){
    productService.saveProduct(product.get("nama").toString(), product.get("deskripsi").toString(), Integer.valueOf(product.get("price").toString()),
            product.get("address").toString(), product.get("image").toString(), Integer.valueOf(product.get("userId").toString()));

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("nama", product.get("nama"));
    responseBody.put("deskripsi", product.get("deskripsi"));
    responseBody.put("price", product.get("price"));
    responseBody.put("address", product.get("address"));
    responseBody.put("image", product.get("image"));
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
                                    "\"image\":\"0\"," +
                                    "\"userId\":\"1\"" +
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
                    "\"image\":\"0\"," +
                    "\"userId\":\"1\"" +
                    "}")
            @RequestBody Map<String, Object> product){

        productService.saveProduct(product.get("nama").toString(), product.get("deskripsi").toString(), Integer.valueOf(product.get("price").toString()),
                product.get("address").toString(), product.get("image").toString(), Integer.valueOf(product.get("userId").toString()));

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", product.get("id"));
        responseBody.put("nama", product.get("nama"));
        responseBody.put("deskripsi", product.get("deskripsi"));
        responseBody.put("price", product.get("price"));
        responseBody.put("address", product.get("address"));
        responseBody.put("image", product.get("image"));
        responseBody.put("userId", product.get("userId"));

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
    public ResponseEntity<Map<String, Object>> deleteProductById(
            @Parameter(description = "add id to delete the product item")
            @PathVariable("id") long id){
        Optional<Product> product = productService.deleteProductById(id);

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
        List<Product> users = productService.getProductByUsername(username);
        for (Product usr : users){
            LOG.info("{}      {}     {}      {}    {}     {}    {}   ", usr.getNama(), usr.getDeskripsi(), usr.getAddress(),
                    usr.getImage(), usr.getPrice(), usr.getUserId().getUserId(), usr.getUserId().getUsername());
        }
        return ResponseEntity.accepted().body(productService.getProductByUsername(username));
    }
}
