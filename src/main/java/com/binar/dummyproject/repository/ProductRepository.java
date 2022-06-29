package com.binar.dummyproject.repository;

import com.binar.dummyproject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository <Product, Long> {

    @Modifying
    @Query(value = "update product set product_name=:product_name, product_image=:product_image, product_description=:product_description," +
            "product_price=:product_price, product_category=:product_category where product_id=:product_id", nativeQuery = true)
    void updateProduct (@Param("product_name") String productName,
                        @Param("product_image") String productImage,
                        @Param("product_description") String productDescription,
                        @Param("product_price") Integer productPrice,
                        @Param("product_category") String productCategory,
                        @Param("product_id") Long productId);

    @Modifying
    @Query(value = "delete from product where product_id =:product_id", nativeQuery = true)
    void deleteProductById(@Param("product_id") Long productId);

    @Modifying
    @Query(value = "select * from product p where p.product_id =:product_id", nativeQuery = true)
    List<Product> findProductByProductId(@Param("product_id") Long product_id);


    @Modifying
    @Query(value = "select * from product p " +
            "join users u on u.user_id = p.user_id" +
            " where u.username =:username", nativeQuery = true)
    List<Product> findProductByUsername (String username);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    Page<Product> findByProductCategoryContaining(String productCategory, Pageable pageable);

    Page<Product> findByProductNameAndProductCategory(String productName, String productCategory, Pageable pageable);

    Page<Product> findByProductNameContainingAndProductCategoryContaining(String productName, String productCategory, Pageable pageable);

}
