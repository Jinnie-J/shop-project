package com.maker.shop.entity;

import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name= "product")
@ToString
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String product_name;

    private Long product_price;

    private String product_category;

    private String product_brand;

    private Long product_sale;

    private String product_gender;

}
