package com.maker.shop.repository;

import com.maker.shop.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c, m, p, ps, pImg from Cart c " +
            "left outer join Member m on c.user=m " +
            "left outer join Product p on c.product =p " +
            "left outer join ProductSize ps on c.productSize=ps " +
            "left outer join ProductImage pImg on pImg.product=p " +
            "where m.email= :email "
    )
    Page<Object[]> getCartListPage(Pageable pageable, @Param("email")String email);
}

