package com.maker.shop.repository;

import com.maker.shop.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r, rImg, p, u FROM Review r " +
            "LEFT OUTER JOIN ReviewImage rImg ON rImg.review = r " +
            "LEFT JOIN Product p on r.product= p "+
            "LEFT JOIN r.user u "+
            "GROUP BY r ")
    Page<Object[]> getReviewListPage(Pageable pageable); //페이지 처리

    @Query("select r, rImg, p, u from Review r " +
            "left outer join ReviewImage rImg on rImg.review = r " +
            "left join Product p on r.product = p "+
            "left join r.user u "+
            "where r.rno = :rno ")
    List<Object[]> getReviewAll(Long rno); //특정 리뷰 조회
}
