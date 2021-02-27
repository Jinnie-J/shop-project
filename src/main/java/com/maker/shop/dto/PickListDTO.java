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
public class PickListDTO {

    private Long pickNo;
    //member
    private String userEmail;
    private String userName;
    private LocalDateTime regDate;
    //product
    private String pno;
    private String productName;

    //product image
    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();
}
