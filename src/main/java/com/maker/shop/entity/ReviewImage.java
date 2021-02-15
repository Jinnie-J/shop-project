package com.maker.shop.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "review")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long imgNo;

    private String uuid;

    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;
}
