package com.binar.dummyproject.repository.productrepository;

import com.binar.dummyproject.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository <Product, Integer> {

    @Modifying
    @Query(value = "delete from product where product_id =:product_id", nativeQuery = true)
    void deleteProductByProductId(@Param("product_id") Integer productId);

}
