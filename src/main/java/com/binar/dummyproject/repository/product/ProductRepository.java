package com.binar.dummyproject.repository.product;

import com.binar.dummyproject.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository <Product, Long> {

    @Modifying
    @Query(value = "select * from product ", nativeQuery = true)
    List<Product> getAllProductProp();

    @Modifying
    @Query(value = "update product set product_name=:product_name, product_description=:product_description," +
            "product_price=:product_price, product_category=:product_category, product_status=:product_status, " +
            "url=:url, url2=:url2, url3=:url3, url4=:url4 , local_date_time=:local_date_time where product_id=:product_id", nativeQuery = true)
    void updateProduct (@Param("product_name") String productName,
                        @Param("product_description") String productDescription,
                        @Param("product_price") Integer productPrice,
                        @Param("product_category") String productCategory,
                        @Param("product_status") String productStatus,
                        @Param("product_id") Long productId,
                        @Param("url")String url,
                        @Param("url2")String url2,
                        @Param("url3")String url3,
                        @Param("url4")String url4,
                        @Param("local_date_time") LocalDateTime localDateTime);

    @Modifying
    @Query(value = "delete from product where product_id =:product_id", nativeQuery = true)
    void deleteProductById(@Param("product_id") Long productId);

    @Modifying
    @Query(value = "select * from product p where p.product_id =:product_id", nativeQuery = true)
    List<Product> findProductByProductId(@Param("product_id") Long productId);

    @Modifying
    @Query(value = "select * from product p where p.user_id=:user_id", nativeQuery = true)
    List<Product> findProductByUserId (@Param("user_id") Integer userId);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    Page<Product> findByProductCategoryContaining(String productCategory, Pageable pageable);

    Page<Product> findByProductNameContainingAndProductCategoryContainingAndProductStatus(String productName, String productStatus, String productCategory, Pageable pageable);

    @Query(value="select * from product where product_id=:productId", nativeQuery = true)
    Product getDetailProductById (Long productId);

    @Query(value = "select * from product p where p.product_id =:productId", nativeQuery = true)
    Product findProductById(Long productId);

    @Modifying
    @Query(value = "update product set product_status ='Sold' where product_id=:productId and user_id=:userId", nativeQuery = true)
    void updateProductStatusSold (Long productId, Integer userId);

    @Query(value = "select * from product where product_name=:productName and user_id=:userId", nativeQuery=true)
    Product findByProductName (String productName, Integer userId);

    @Query(value = "select * from product where product_status like 'Sold' and user_id=:userId", nativeQuery = true)
    List<Product> getProductSold (Integer userId);

}
