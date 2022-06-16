package com.binar.dummyproject.repository;

import com.binar.dummyproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository <Product, Integer> {

    @Modifying
    @Query(value = "update product set nama=:nama, image=:image, deskripsi=:deskripsi," +
            "price=:price, address=:address where id=:id", nativeQuery = true)
    void updateProduct (@Param("nama") String nama,
                        @Param("image") String image,
                        @Param("deskripsi") String deskripsi,
                        @Param("price") Integer price,
                        @Param("address") String address,
                        @Param("id") long id);

    @Modifying
    @Query(value = "delete from product where id =:id", nativeQuery = true)
    void deleteProductById(@Param("id") long id);

    @Modifying
    @Query(value = "select * from product p " +
            "join users u on u.user_id = p.user_id" +
            " where u.username =:username", nativeQuery = true)
    List<Product> findProductByUsername (String username);

}
