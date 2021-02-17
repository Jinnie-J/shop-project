package com.maker.shop.service;

import com.maker.shop.dto.*;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.ProductImage;
import com.maker.shop.entity.ProductSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProductService {


    PageResultDTO<ProductDTO, Object[]> getProductList(PageRequestDTO requestDTO, String gender,String category);

    default ProductDTO entitiesToDTO(Product product, List<ProductImage> productImages){

        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .category(product.getCategory())
                .gender(product.getGender())
                .build();

        List<ProductImageDTO> productImageDTOList = productImages.stream().
                map(productImage -> {

            return ProductImageDTO.builder().imgName(productImage.getImgName())
                    .path(productImage.getPath())
                    .uuid(productImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        productDTO.setImageDTOList(productImageDTOList);

        return productDTO;
    }

    String register(ProductDTO productDTO);

    default Map<String, Object> dtoToEntity(ProductDTO productDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Product product = Product.builder()
                .pno(productDTO.getPno())
                .name(productDTO.getName())
                .gender(productDTO.getGender())
                .sale(productDTO.getSale())
                .brand(productDTO.getBrand())
                .category(productDTO.getCategory())
                .price(productDTO.getPrice())
                .build();

        entityMap.put("product", product);
        List<ProductImageDTO> imageDTOList = productDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0){
            List<ProductImage> productImageList = imageDTOList.stream().map(productImageDTO -> {

                ProductImage productImage = ProductImage.builder()
                        .path(productImageDTO.getPath())
                        .imgName(productImageDTO.getImgName())
                        .uuid(productImageDTO.getUuid())
                        .product(product)
                        .build();
                return productImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", productImageList);
        }

        List<ProductSizeDTO> sizeDTOList = productDTO.getProductSizeDTOList();

        if(sizeDTOList != null && sizeDTOList.size() > 0){
            List<ProductSize> productSizeList = sizeDTOList.stream().map(productSizeDTO -> {

                ProductSize productSize = ProductSize.builder()
                        .size(productSizeDTO.getSize())
                        .amount(productSizeDTO.getAmount())
                        .product(product)
                        .build();

                return productSize;

            }).collect(Collectors.toList());

            entityMap.put("sizeList", productSizeList);
        }


        return entityMap;
    }
}
