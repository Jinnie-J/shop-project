package com.maker.shop.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name= "product")
@ToString
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pno;

    private String name;

    private Long price;

    private String category;

    private String brand;

    private Long sale;

    private String gender;

}
