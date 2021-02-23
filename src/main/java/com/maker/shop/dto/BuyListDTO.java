package com.maker.shop.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyListDTO {

    private Long Bno;
    private Long amount;
    private String status; //배송상태
    private LocalDateTime regDate;

    //member
    private String userEmail;

    //product
    private String pno;
    private String productName;
    private Long price;

    //productsize
    private Long size;

    //product image
    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();
}
