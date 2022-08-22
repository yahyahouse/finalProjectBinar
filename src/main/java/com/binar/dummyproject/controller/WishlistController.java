package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.offer.OfferResponseNew;
import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.users.Users;
import com.binar.dummyproject.model.wishlist.WishlishResponses;
import com.binar.dummyproject.model.wishlist.Wishlist;
import com.binar.dummyproject.service.product.ProductService;
import com.binar.dummyproject.service.users.UsersService;
import com.binar.dummyproject.service.wishlist.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Wishlist", description = "API for processing various operations with Wishlist entity")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "add a wishlist when a buyer is interested in a product")
    @PostMapping("/add-wishlist/")
    public ResponseEntity<WishlishResponses> addWishlist(
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Long productId
    ){
        Users users = usersService.findByUserId(userId);
        Product product = productService.getProductById(productId);
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(users);
        wishlist.setProductId(product);
        wishlistService.saveWishlist(productId, userId);
        return new ResponseEntity<>(new WishlishResponses(users, product, wishlist),HttpStatus.OK);
    }

    @Operation(summary = "Delete wishlist")
    @DeleteMapping("/delete-wishlist/{wishlistId}")
    public ResponseEntity<Map<String, Object>> deleteWishlistByID(
            @Parameter(description = "add id to delete wishlist")
            @PathVariable("wishlistId") Long wishlistId){
        Optional<Wishlist> wishlist = wishlistService.deleteProductById(wishlistId);

        Map<String, Object> response = new HashMap<>();
        if(wishlist.isPresent()){
            response.put("success", true);
            response.put("deletedData", wishlist);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @Operation(summary = "Get wishlist by user id")
    @GetMapping(value = "/get-wishlist-user/{userId}")
    public ResponseEntity<List<Wishlist>> getProductByUserId(@PathVariable("userId") Integer userId){
        wishlistService.getWishlistsByUserId(userId);
        return ResponseEntity.accepted().body(wishlistService.getWishlistsByUserId(userId));
    }
}
