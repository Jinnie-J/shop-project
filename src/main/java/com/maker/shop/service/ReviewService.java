package com.maker.shop.service;

import com.maker.shop.dto.ReviewDTO;
import com.maker.shop.dto.ReviewImageDTO;
import com.maker.shop.entity.Member;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.Review;
import com.maker.shop.entity.ReviewImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ReviewService {

    Long register(ReviewDTO reviewDTO);

    default Map<String,Object> dtoToEntity(ReviewDTO reviewDTO){
        Map<String, Object> entityMap=new HashMap<>();

        Member member = Member.builder().email(reviewDTO.getWriterEmail()).build();
        Product product = Product.builder().pno(reviewDTO.getPno()).build();

        Review review = Review.builder()
                .rno(reviewDTO.getRno())
                .product(product)
                .user(member)
                .title(reviewDTO.getTitle())
                .content(reviewDTO.getContent())
                .grade(reviewDTO.getGrade())
                .build();

        entityMap.put("review",review);

        List<ReviewImageDTO> imageDTOList= reviewDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size()>0){
            List<ReviewImage> reviewImageList = imageDTOList.stream().
                    map(reviewImageDTO -> {
                        ReviewImage reviewImage = ReviewImage.builder()
                                .path(reviewImageDTO.getPath())
                                .imgName(reviewImageDTO.getImgName())
                                .uuid(reviewImageDTO.getUuid())
                                .review(review)
                                .build();
                        return reviewImage;
                    }).collect(Collectors.toList());
            entityMap.put("imgList", reviewImageList);
        }
        return entityMap;
    }
}
