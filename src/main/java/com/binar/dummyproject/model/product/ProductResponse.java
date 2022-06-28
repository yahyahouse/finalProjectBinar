package com.binar.dummyproject.model.product;

import com.binar.dummyproject.model.Users;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private Integer userId;
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private String productCategory;
    private List<String> url;

    public ProductResponse(){

    }

    public ProductResponse(Integer userId, Long productId, String productName, String productDescription,
                           Integer productPrice, String productCategory, List<String> url) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.url = url;

    }
}
