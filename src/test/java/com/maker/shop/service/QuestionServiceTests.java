package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.QuestionDTO;
import com.maker.shop.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceTests {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testRegister(){
        QuestionDTO dto = QuestionDTO.builder()
                .title("test.")
                .content("Test...")
                .pno("test1")
                .writerEmail("user1@maker.com")
                .build();
        Long qno= questionService.register(dto);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<QuestionDTO, Object[]> result = questionService.getList(pageRequestDTO);

        for(QuestionDTO questionDTO: result.getDtoList()){
            System.out.println(questionDTO);
        }
    }
    @Test
    public void testGet(){
        Long qno = 1L;
        QuestionDTO questionDTO = questionService.get(qno);
        System.out.println(questionDTO);
    }

    @Test
    public void testRemove(){
        Long qno=1L;
        questionService.removeWithReplies(qno);
    }
    @Test
    public void testModify(){
        QuestionDTO questionDTO = QuestionDTO.builder()
                .qno(2L)
                .title("제목 변경")
                .content("내용 변경")
                .build();
        questionService.modify(questionDTO);
    }
}
