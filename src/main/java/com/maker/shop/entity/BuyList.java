package com.maker.shop.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude={"product","productSize","user"})
public class BuyList extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Bno;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="email")
    private Member user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "sizeNo")
    private ProductSize productSize;

    private String status;

    private Long amount;

}
