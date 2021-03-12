package com.maker.shop.repository;

import com.maker.shop.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r, rImg FROM Review r " +
            "LEFT OUTER JOIN ReviewImage rImg ON rImg.review = r " +
            "GROUP BY r ")
    Page<Object[]> getReviewListPage(Pageable pageable); //페이지 처리

    @Query("select r, rImg " +
            " from Review r left outer join ReviewImage rImg on rImg.review = r " +
            " where r.rno = :rno ")
    List<Object[]> getReviewAll(Long rno); //특정 리뷰 조회
}
