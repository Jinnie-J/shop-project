package com.maker.shop.service;

import com.maker.shop.dto.*;
import com.maker.shop.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public interface MypageService {

    //찜리스트에 추가
    Long registerPL(PickListDTO pickdto);
    //찜한상품 목록처리
    PageResultDTO<PickListDTO, Object[]> getPickList(PageRequestDTO pageRequestDTO,String email);
    //조회
    PickListDTO getPL(Long pickNo);
    //삭제
    void removePL(Long pickNo);

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

    //장바구니에 추가
    Long registerCart(CartDTO cartDTO);
    //장바구니 목록처리
    PageResultDTO<CartDTO, Object[]> getCartList(PageRequestDTO pageRequestDTO,String email);
    //조회
    CartDTO getCart(Long cno);
    //삭제
    void removeCart(Long cno);

    default Cart dtoToEntity(CartDTO cartDTO){
        Member member=Member.builder().email(cartDTO.getUserEmail()).build();
        Product product=Product.builder().name(cartDTO.getProductName()).build();
        ProductSize productSize=ProductSize.builder().size(cartDTO.getSize()).build();

        Cart cart=Cart.builder()
                .cno(cartDTO.getCno())
                .user(member)
                .product(product)
                .productSize(productSize)
                .amount(cartDTO.getAmount())
                .status(cartDTO.getStatus())
                .build();
        return cart;
    }

    default CartDTO entitiesToDTO(Cart cart, Member member, Product product,
                                  ProductSize productSize, List<ProductImage> productImages){
        CartDTO cartDTO=CartDTO.builder()
                .cno(cart.getCno())
                .userEmail(member.getEmail())
                .pno(product.getPno())
                .productName(product.getName())
                .size(productSize.getSize())
                .amount(cart.getAmount())
                .status(cart.getStatus())
                .build();

        List<ProductImageDTO> productImageDTOList = productImages.stream().
                map(productImage -> {

                    return ProductImageDTO.builder().imgName(productImage.getImgName())
                            .path(productImage.getPath())
                            .uuid(productImage.getUuid())
                            .build();
                }).collect(Collectors.toList());

        cartDTO.setImageDTOList(productImageDTOList);
        return cartDTO;
    }


}
