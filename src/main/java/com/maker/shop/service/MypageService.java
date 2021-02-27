package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.PickListDTO;
import com.maker.shop.dto.ProductImageDTO;
import com.maker.shop.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public interface MypageService {

    //찜리스트에 추가
    Long register(PickListDTO pickdto);

    //목록처리
    PageResultDTO<PickListDTO, Object[]> getList(PageRequestDTO pageRequestDTO,String email);

    //조회
    PickListDTO get(Long pickNo);

    //삭제
    void removeWithReplies(Long pickNo);

    default PickList dtoToEntity(PickListDTO pickdto){
        Member member=Member.builder().email(pickdto.getUserEmail()).build();
        Product product=Product.builder().name(pickdto.getProductName()).build();

        PickList pickList=PickList.builder()
                .pickNo(pickdto.getPickNo())
                .user(member)
                .product(product)

                .build();
        return pickList;
    }

    default PickListDTO entitiesToDTO(PickList pickList, Member member, Product product
                                    ,List<ProductImage> productImages){
        PickListDTO pickListDTO=PickListDTO.builder()
                .pickNo(pickList.getPickNo())
                .userEmail(member.getEmail())
                .userName(member.getName())
                .regDate(pickList.getRegDate())
                .pno(product.getPno())
                .productName(product.getName())
                .build();

        List<ProductImageDTO> productImageDTOList = productImages.stream().
                map(productImage -> {

                    return ProductImageDTO.builder().imgName(productImage.getImgName())
                            .path(productImage.getPath())
                            .uuid(productImage.getUuid())
                            .build();
                }).collect(Collectors.toList());

        pickListDTO.setImageDTOList(productImageDTOList);

        return pickListDTO;
    }
}
