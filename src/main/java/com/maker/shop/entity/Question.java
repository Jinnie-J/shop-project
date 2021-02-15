package com.maker.shop.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude={"product","member"})
public class Question extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long qno;

    @ManyToOne(fetch= FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    private Member member;

    @Column(length =100, nullable = false)
    private String title;

    @Column(length=1500, nullable = false)
    private String content;
}
