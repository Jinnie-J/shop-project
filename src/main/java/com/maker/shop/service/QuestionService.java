package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.QuestionDTO;
import com.maker.shop.entity.Member;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.Question;

public interface QuestionService {

    Long register(QuestionDTO dto);

    PageResultDTO<QuestionDTO, Object[]> getList(PageRequestDTO pageRequestDTO); //목록처리

    QuestionDTO get(Long qno);

    void removeWithReplies(Long qno);

    void modify(QuestionDTO questionDTO);

    default Question dtoToEntity(QuestionDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Product product = Product.builder().pno(dto.getPno()).build();

        Question question = Question.builder()
                .qno(dto.getQno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(member)
                .product(product)
                .build();
        return question;
    }

    default QuestionDTO entityToDTO(Question question, Member member,
                                    Product product, Long replyCount){
        QuestionDTO questionDTO=QuestionDTO.builder()
                .qno(question.getQno())
                .title(question.getTitle())
                .content(question.getContent())
                .regDate(question.getRegDate())
                .modDate(question.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .pno(product.getPno())
                .replyCount(replyCount.intValue())
                .build();
        return questionDTO;
    }
}
