package com.maker.shop.repository;

import com.maker.shop.entity.PickList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PickListRepository extends JpaRepository<PickList, Long> {

    //member,product,productimage
    @Query("select pi, m, p, pImg from PickList pi " +
            "left outer join Member m on pi.user=m " +
            "left outer join Product p on pi.product =p " +
            "left outer join ProductImage pImg on pImg.product=p " +
            "where m.email= :email "
    )
    Page<Object[]> getPickListPage(Pageable pageable, @Param("email")String email);
}
