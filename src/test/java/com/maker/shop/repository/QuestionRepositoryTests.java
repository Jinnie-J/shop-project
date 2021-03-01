package com.maker.shop.repository;

import com.maker.shop.dto.QuestionDTO;
import com.maker.shop.entity.Member;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class QuestionRepositoryTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1,10).forEach(i -> {
            Member member= Member.builder().email("user"+i+"@maker.com").build();
            Product product=Product.builder().pno("test1").build();
            Question question=Question.builder()
                    .title("Title..."+i)
                    .content("Content...."+i)
                    .user(member)
                    .product(product)
                    .build();
            questionRepository.save(question);
        });
    }

    @Transactional
    @Test
    public void testRead1(){
        Optional<Question> result= questionRepository.findById(1L);

        Question question = result.get();
        System.out.println(question);
        System.out.println(question.getUser());
    }

    @Test
    public void testReadWithWriter(){
        Object result = questionRepository.getQuestionWithUser(1L);
        Object[] arr= (Object[])result;

        System.out.println("-------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetQuestionWithReply(){
        List<Object[]> result = questionRepository.getQuestionWithReply(1L);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("qno").descending());
        Page<Object[]> result = questionRepository.getQuestionWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[])row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3(){
        Object result = questionRepository.getQuestionByQno(1L);
        Object[] arr= (Object[])result;
        System.out.println(Arrays.toString(arr));
    }

}
