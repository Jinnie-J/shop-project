package com.maker.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String pno;

    private String name;

    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();

    private Long price;

    private String category;

    private String brand;

    private Long sale;

    private String gender;

    @Builder.Default
    private List<ProductSizeDTO> productSizeDTOList = new ArrayList<>();

}
