package com.maker.shop.repository;

import com.maker.shop.entity.BuyList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuyListRepository extends JpaRepository<BuyList, Long> {

    @Query("select b, m, p, ps, pImg from BuyList b " +
            "left outer join Member m on b.user=m " +
            "left outer join Product p on b.product= p " +
            "left outer join ProductSize ps on b.productSize=ps "+
            "left outer join ProductImage pImg on pImg.product= p " +
            "where m.email= :email "
    )
    Page<Object[]> getBuyListPage(Pageable pageable, @Param("email")String email);
}
