package com.maker.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "product")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgNum;

    private String uuid;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pno")
    private Product product;
}
