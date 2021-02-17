package com.maker.shop.repository;

import com.maker.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p, pImg from Product p " +
            "left outer join ProductImage pImg on pImg.product = p WHERE p.gender = :gender AND p.category LIKE (CASE WHEN :category is not null then :category ELSE '11%' END) " +
            "group by pImg.uuid")
    Page<Object[]> getProductListPage(Pageable pageable, @Param("gender")String gender,@Param("category") String category);
}
