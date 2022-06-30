package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductImage;
import com.binar.dummyproject.model.UploadResponse;
import com.binar.dummyproject.model.Users;
import com.binar.dummyproject.model.product.ProductResponse;
import com.binar.dummyproject.service.product.ProductService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Tag(name = "Product", description = "API for processing various operations with Product entity")
@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService productService;

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dummyprojectbinar",
            "api_key", "221166829538913",
            "api_secret", "5KfEb789PD2SosIE12zXehlidwM"));

    @Operation(summary = "Add a new product by seller")
    @PostMapping("/seller/add-product/{userId}")
    public ResponseEntity<Map<String, Object>> addProduct(
            @PathVariable("userId") Integer userId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("product_name") String productName,
            @RequestParam("product_description") String productDescription,
            @RequestParam("product_price") Integer productPrice,
            @RequestParam("product_category") String productCategory,
            @RequestParam("productId") Long productId)
            throws IOException{
        Integer size = files.length;
        String[] url = new String[size];
        for(int i = 0; i<size; i++){
            File file = new File(files[i].getOriginalFilename());
            FileOutputStream os = new FileOutputStream(file);
            os.write(files[i].getBytes());
            os.close();
            Map result = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap("product_image_id", "product_name"));
            url[i] = result.get("url").toString();
            UploadResponse responses = new UploadResponse();
            responses.setMessage("Success upload images");
            responses.setUrl(url);

            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setProductDescription(productDescription);
            product.setProductPrice(productPrice);
            product.setProductCategory(productCategory);
            ProductImage productImage = new ProductImage();
            productImage.setProductImageName(files[i].getOriginalFilename());
            productImage.setProductImageFile(files[i].getBytes());
            Users users = new Users();
            users.setUserId(userId);
            product.setUserId(users);
            productService.saveProduct(productName, productDescription, productPrice, productCategory, userId, productId);
            productService.saveProdductImage(productId, files[i].getOriginalFilename(), files[i].getBytes());

        }
        return new ResponseEntity(new ProductResponse(userId, productId, productName, productDescription,
                productPrice, productCategory, Arrays.asList(url)), HttpStatus.OK);
    }


    @Operation(summary = "Update existing product by seller")
    @PutMapping("/seller/update-product/{userId}/{productId}")
    public ResponseEntity<Map<String, Object>> updateProduct (
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Long productId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("product_name") String productName,
            @RequestParam("product_description") String productDescription,
            @RequestParam("product_price") Integer productPrice,
            @RequestParam("product_category") String productCategory)
        throws IOException{

        Integer size = files.length;
        String[] url = new String[size];
        for(int i = 0; i<size;i++){
            File file = new File(files[i].getOriginalFilename());
            FileOutputStream os = new FileOutputStream(file);
            os.write(files[i].getBytes());
            os.close();
            Map result = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap("product_image_id", "product_name"));
            url[i] = result.get("url").toString();
            UploadResponse responses = new UploadResponse();
            responses.setMessage("Success upload images");
            responses.setUrl(url);
            productService.saveProdductImage(productId, files[i].getOriginalFilename(), files[i].getBytes());
            productService.updateProduct(productId, productName, productDescription, productPrice, productCategory);
        }
        return new ResponseEntity(new ProductResponse(userId, productId, productName, productDescription,
                productPrice, productCategory, Arrays.asList(url)), HttpStatus.OK);
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/seller/delete-product/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProductById(
            @Parameter(description = "add id to delete the product item")
            @PathVariable("productId") Long productId){
        productService.deleteProductImage(productId);
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


    @Operation(summary = "Get product by seller username")
    @GetMapping(value = "/seller/get-product-seller/{username}")
    public ResponseEntity<List<Product>> getProductByUserId(@PathVariable("username") String username){
        productService.getProductByUsername(username);
        return ResponseEntity.accepted().body(productService.getProductByUsername(username));
    }


    @GetMapping("/seller/get-product-sortedBy-productName")
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
