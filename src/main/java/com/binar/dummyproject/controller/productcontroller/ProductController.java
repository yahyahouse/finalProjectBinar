package com.binar.dummyproject.controller.productcontroller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.service.productservice.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private static final String PRODUCTNAME = "productName";
    private static final String PRODUCTCATEGORY = "productCategory";
    private static final String PRODUCTDESC = "productDesc";

    @Autowired
    private ProductService productService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New product added",
            content = {@Content(
                    mediaType = "application/json",
                    schema =@Schema(example = "{" +
                            "\"productName\":\"Jam tangan\"," +
                            "\"productCategory\":\"Fashion\"," +
                            "\"productDesc\":\"Untuk melihat waktu\"" +
                            "}")
            )})
    })
    @Operation(summary = "Add a new product by seller")
    @PostMapping("/seller/add-product")
    public ResponseEntity<Map<String, Object>> addProduct (
            @Schema(example = "{" +
                    "\"productName\":\"Jam tangan\"," +
                    "\"productCategory\":\"Fashion\"," +
                    "\"productDesc\":\"Untuk melihat waktu\"" +
                    "}")
            @RequestBody Map<String, Object> product){

    productService.saveProduct(product.get(PRODUCTNAME).toString(), product.get(PRODUCTCATEGORY).toString(), product.get(PRODUCTDESC).toString());

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put(PRODUCTNAME, product.get(PRODUCTNAME));
    responseBody.put(PRODUCTCATEGORY, product.get(PRODUCTCATEGORY));
    responseBody.put(PRODUCTDESC, product.get(PRODUCTDESC));

    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.put("dummyProject", Arrays.asList("halo"));
    return ResponseEntity.ok().body(responseBody);
    }

//    @Operation(summary = "Show all products by seller")
//    @GetMapping("/seller/show-products")
//    public ResponseEntity<List<Product>> showall (){
//
//    }
//
//    @Operation(summary = "Update an existing product by seller")
//    @PutMapping("/seller/update-product")
//    public ResponseEntity<Map<String, Object>> updateProduct (){
//
//    }

    @ApiResponses(value = {
            @ApiResponse( content = {
                    @Content(examples = {})
            }, responseCode = "202", description = "Success deleted a product")
    })
    @Operation(summary = "Delete a product")
    @DeleteMapping("/seller/delete-product/{productId}")
    public ResponseEntity<String> deleteProductByProductId(
            @Parameter(description = "add productId to delete the product item")
            @PathVariable("productId") Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.accepted().body("Deleted product "+productId+" success");
    }
}
