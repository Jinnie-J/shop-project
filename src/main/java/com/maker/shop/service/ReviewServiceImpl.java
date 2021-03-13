package com.maker.shop.service;

import com.maker.shop.dto.ReviewDTO;
import com.maker.shop.entity.Review;
import com.maker.shop.entity.ReviewImage;
import com.maker.shop.repository.ReviewImageRepository;
import com.maker.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final ReviewImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(ReviewDTO reviewDTO) {

        Map<String, Object> entityMap= dtoToEntity(reviewDTO);
        Review review = (Review) entityMap.get("review");
        List<ReviewImage> reviewImageList = (List<ReviewImage>)
                entityMap.get("imgList");

        reviewRepository.save(review);

        reviewImageList.forEach(reviewImage -> {
            imageRepository.save(reviewImage);
        });
        return review.getRno();
    }
}
