package com.maker.shop.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude={"product","user"})
public class Review extends Serializers.Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member user;

    @Column(length =100, nullable = false)
    private String title;

    @Column(length=1500, nullable = false)
    private String content;

    private String score; //별점

}
