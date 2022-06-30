package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.product.Product;
import com.binar.dummyproject.model.Wishlist;
import com.binar.dummyproject.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Operation(summary = "add a wishlist when a buyer is interested in a product")
    @PostMapping("/add-wishlist/")
    public ResponseEntity<Map<String, Object>> addWishlistByProductIdAndUserId (
            @Schema(example = "{" +
                    "\"productId\":\"1\"," +
                    "\"userId\":\"1\"" +
                    "}")
            @RequestBody Map<String, Object> wishlist){
        wishlistService.saveWishlist(Long.valueOf(wishlist.get("productId").toString()),Integer.valueOf(wishlist.get("userId").toString()));

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("productId", wishlist.get("productId"));
        responseBody.put("userId", wishlist.get("userId"));

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("dummyProject", Arrays.asList("halo"));
        return ResponseEntity.ok()
                .header("dummyProject", "Test")
                .body(responseBody);
    }

    @ApiResponses(value = {
            @ApiResponse( content = {
                    @Content(examples = {})
            }, responseCode = "204", description = "Success deleted wishlist")
    })
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
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(examples = {})
            }, responseCode = "202", description = "Success show wishlist by user id")
    })
    @Operation(summary = "Get wishlist by user id")
    @GetMapping(value = "/get-wishlist-user/{userId}")
    public ResponseEntity<List<Wishlist>> getProductByUserId(@PathVariable("userId") Integer userId){
        wishlistService.getWishlistsByUserId(userId);
        return ResponseEntity.accepted().body(wishlistService.getWishlistsByUserId(userId));
    }
}
