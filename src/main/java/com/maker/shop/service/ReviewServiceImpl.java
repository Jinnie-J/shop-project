package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.ReviewDTO;
import com.maker.shop.entity.Member;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.Review;
import com.maker.shop.entity.ReviewImage;
import com.maker.shop.repository.ReviewImageRepository;
import com.maker.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

        log.info(reviewImageList);
        reviewImageList.forEach(reviewImage -> {
            imageRepository.save(reviewImage);
        });
        return review.getRno();
    }

    @Override
    public PageResultDTO<ReviewDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("rno").descending());

        Page<Object[]> result=reviewRepository.getReviewListPage(pageable);

       log.info("serviceimple------------------"+ reviewRepository.getReviewListPage(pageable));
        Function<Object[], ReviewDTO> fn= (arr -> entitiesToDTO(
                (Review)arr[0],
                (List<ReviewImage>)(Arrays.asList((ReviewImage)arr[1])),
                (Product)arr[2],
                (Member)arr[3])
        );

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public ReviewDTO getReview(Long rno) {

        List<Object[]> result = reviewRepository.getReviewAll(rno);
        Review review = (Review) result.get(0)[0];

        List<ReviewImage> reviewImageList = new ArrayList<>();
        result.forEach(arr ->{
            ReviewImage reviewImage = (ReviewImage)arr[1];
            reviewImageList.add(reviewImage);
        });

        Product product= (Product) result.get(0)[2];
        Member member= (Member) result.get(0)[3];

        return entitiesToDTO(review, reviewImageList, product, member);
    }

    @Transactional
    @Override
    public void modify(ReviewDTO reviewDTO) {
        Review review= reviewRepository.getOne(reviewDTO.getRno());

        if(review != null){
            review.changeTitle(reviewDTO.getTitle());
            review.changeContent(reviewDTO.getContent());

            reviewRepository.save(review);
        }
    }

    @Override
    public void removeReview(Long rno) {
        reviewRepository.deleteById(rno);
        imageRepository.deleteById(rno);

    }
}
