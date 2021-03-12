package com.maker.shop.repository;

import com.maker.shop.entity.Member;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.Review;
import com.maker.shop.entity.ReviewImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertReviews(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Review review= Review.builder().title("Review..." + i).content("Content..."+i).build();
            System.out.println("--------------------------------");
            reviewRepository.save(review);

            int count=(int)(Math.random()*5)+1; //1,2,3,4

            for(int j=0; j<count;j++){
                ReviewImage reviewImage = ReviewImage.builder().uuid(UUID.randomUUID().toString())
                        .review(review)
                        .imgName("test"+j+".jpg").build();

                imageRepository.save(reviewImage);
            }
            System.out.println("==============================");
        });
    }

    @Test
    public void insertProductReviews(){
        IntStream.rangeClosed(1,5).forEach(i-> {
            String pno="test1";
            String email="11@naver.com";
            Member member=Member.builder().email(email).build();
            Product product= Product.builder().pno(pno).build();

            Review productReview = Review.builder()
                    .user(member)
                    .product(product)
                    .grade((int)(Math.random()*5)+1)
                    .title("상품제목")
                    .content("상품내용")
                    .build();
            reviewRepository.save(productReview);
        });
    }
    @Test
    public void testGetReviewWithAll(){
        List<Object[]> result = reviewRepository.getReviewAll(94L);
        System.out.println(result);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }
}
