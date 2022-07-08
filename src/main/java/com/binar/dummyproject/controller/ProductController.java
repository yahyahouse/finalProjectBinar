package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.product.ProductDetailResponse;
import com.binar.dummyproject.model.product.ProductImage;
import com.binar.dummyproject.model.UploadResponse;
import com.binar.dummyproject.model.users.UserDetailsImpl;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.model.product.ProductResponse;
import com.binar.dummyproject.repository.product.ProductRepository;
import com.binar.dummyproject.service.product.ProductService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "Product", description = "API for processing various operations with Product entity")
@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dummyprojectbinar",
            "api_key", "221166829538913",
            "api_secret", "5KfEb789PD2SosIE12zXehlidwM"));

    @CrossOrigin(origins = "https://dummyprojectbinar.herokuapp.com", maxAge = 3600)
    @Operation(summary = "Add a new product by seller")
    @PostMapping("/seller/add-product/{userId}")
    public ResponseEntity<ProductResponse> addProduct(
            @PathVariable("userId") Integer userId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("product_name") String productName,
            @RequestParam("product_description") String productDescription,
            @RequestParam("product_price") Integer productPrice,
            @RequestParam("product_category") String productCategory,
            @RequestParam(defaultValue = "Available", required = false) String productStatus)
            throws IOException{
        Integer size = files.length;
        String[] url = new String[size];
        for(int i = 0; i<size; i++) {
            File file = new File(files[i].getOriginalFilename());
            FileOutputStream os = new FileOutputStream(file);
            os.write(files[i].getBytes());
            os.close();
            Map result = cloudinary.uploader().upload(file,
                    ObjectUtils.asMap("product_image_id", "product_name"));
            url[i] = result.get("url").toString();
        }
            UploadResponse responses = new UploadResponse();
            responses.setMessage("Success upload images");
            responses.setUrl(url[0]);

            Product product = new Product();
            product.setProductName(productName);
            product.setProductDescription(productDescription);
            product.setProductPrice(productPrice);
            product.setProductCategory(productCategory);
            product.setUrl(url[0]);
            product.setUrl2(url[1]);
            product.setUrl3(url[2]);
            product.setUrl4(url[3]);
            product.setProductStatus(productStatus);
            Users users = new Users();
            users.setUserId(userId);
            product.setUserId(users);
//            productRepository.save(product);
            productService.saveProduct(productName, productDescription, productPrice, productCategory, productStatus, userId, url[0], url[1],
                    url[2], url[3]);

        return new ResponseEntity(new ProductResponse(userId,product.getProductId(),productName,productDescription,productPrice,
                productCategory,productStatus,url[0], url[1], url[2], url[3]), HttpStatus.OK);
    }

    @CrossOrigin(origins = "https://dummyprojectbinar.herokuapp.com", maxAge = 3600)
    @Operation(summary = "Update existing product by seller")
    @PutMapping("/seller/update-product/{userId}/{productId}")
    public ResponseEntity<ProductResponse> updateProduct (
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Long productId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("product_name") String productName,
            @RequestParam("product_description") String productDescription,
            @RequestParam("product_status") String productStatus,
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
            responses.setUrl(url[0]);
            productService.saveProdductImage(productId, files[i].getOriginalFilename(), url[i]);
            productService.updateProduct(productId, productName, productDescription, productPrice, productCategory,
                    productStatus);
        }
        return new ResponseEntity(new ProductResponse(userId, productName, productDescription,
                productPrice, productCategory, productStatus, url[0],url[1],url[2],url[3]), HttpStatus.OK);
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/seller/delete-product/{productId}")
    public ResponseEntity<Product> deleteProductById(
            @Parameter(description = "add id to delete the product item")
            @PathVariable("productId") Long productId){
        Optional<ProductImage> product = productService.deleteProductImage(productId);
        Optional<Product> productImage = productService.deleteProductById(productId);
        if(productImage.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Get product by seller userId")
    @GetMapping(value = "/seller/get-product-seller/{userId}")
    public ResponseEntity<ProductResponse> getProductByUserId(@PathVariable("userId") Integer userId){
        List<Product> product = productService.getProductByUserId(userId);
        List<ProductResponse> productResponse =
                product.stream().map(product1 -> new ProductResponse(product1)).collect(
                        Collectors.toList());
        return new ResponseEntity(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get detail product")
    @GetMapping(value = "/buyer/get-detail-product/{productId}")
    public ResponseEntity<ProductResponse> getDetailProductById(@PathVariable("productId") Long productId){
        Product products = productService.getProductDetailByid(productId);
        return new ResponseEntity(new ProductDetailResponse(products), HttpStatus.OK);
    }

    @Operation(summary = "update product status to Sold")
    @PutMapping(value = "/seller/product-status-sold/{userId}/{productId}")
    public ResponseEntity<Map<String , Object>> getUpdateProductStatusSold(
            @PathVariable("productId") Long productId,
            @PathVariable("userId") Integer userId){
        productService.updateStatusProductSold(productId, userId);
        return new ResponseEntity("Status barang sudah berubah menjadi sold", HttpStatus.OK);
    }


}
