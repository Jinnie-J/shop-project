package com.maker.shop.repository;

import com.maker.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //상품목록
    @Query("SELECT p, pImg FROM Product p " +
            "LEFT OUTER JOIN ProductImage pImg ON pImg.product = p WHERE p.gender = :gender AND p.category LIKE (CASE WHEN :category is not null then :category ELSE '11%' END) " +
            "GROUP BY p.pno")
    Page<Object[]> getProductListPage(Pageable pageable, @Param("gender")String gender,@Param("category") String category);

    //상세페이지 상품정보
    @Query("SELECT p, pImg FROM Product p " +
            " LEFT OUTER JOIN ProductImage pImg ON pImg.product = p " +
            " WHERE p.pno = :pno")
    List<Object[]> getProduct(String pno);

    //상세페이지 재고확인
    @Query("SELECT p, ps FROM Product p " +
            "LEFT OUTER JOIN ProductSize ps ON ps.product = p " +
            "WHERE p.pno = :pno")
    List<Object[]> getProductSizeList(String pno);

    //메인 페이지 새상품 출력
    @Query("SELECT p, pImg FROM Product p " +
            " LEFT OUTER JOIN ProductImage pImg ON pImg.product = p " +
            " WHERE p.category = 1106 OR p.category = 1113 GROUP BY p.pno ")
    Page<Object[]> getNewProduct(Pageable pageable);

    //세일 품목 출력
    @Query("SELECT p, pImg FROM Product p " +
            " LEFT OUTER JOIN ProductImage pImg ON pImg.product = p " +
            " WHERE p.sale > 0 GROUP BY p.pno ")
    Page<Object[]> getSaleProduct(Pageable pageable);

}
