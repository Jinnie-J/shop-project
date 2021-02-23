package com.maker.shop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long cno;
    private Long amount;
    private String status;

    //member
    private String userEmail;

    //product
    private String pno;
    private String productName;

    //productsize
    private Long size;

    //product image
    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();

}
