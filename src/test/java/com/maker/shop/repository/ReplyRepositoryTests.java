package com.maker.shop.repository;

import com.maker.shop.entity.Question;
import com.maker.shop.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1,10).forEach(i ->{
            long qno = (long)(Math.random() * 10) +1;
            Question question= Question.builder().qno(qno).build();

            Reply reply= Reply.builder()
                    .text("Reply...."+i)
                    .question(question)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Transactional
    @Test
    public void readReply1(){
        Optional<Reply> result= replyRepository.findById(1L);
        Reply reply =result.get();

        System.out.println(reply);
        System.out.println(reply.getQuestion());
    }

}
