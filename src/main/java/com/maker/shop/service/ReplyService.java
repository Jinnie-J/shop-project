package com.maker.shop.service;

import com.maker.shop.dto.ReplyDTO;
import com.maker.shop.entity.Question;
import com.maker.shop.entity.Reply;

import java.util.List;

public interface ReplyService {

    //댓글 등록
    Long register(ReplyDTO replyDTO);
    //특정 게시물의 댓글 목록
    List<ReplyDTO> getList(Long qno);
    //댓글 수정
    void modify(ReplyDTO replyDTO);
    //댓글 삭제
    void remove(Long replyNo);

    //replydto 를 reply객체로 변환
    default Reply dtoToEntity(ReplyDTO replyDTO){
        Question question = Question.builder().qno(replyDTO.getQno()).build();

        Reply reply = Reply.builder()
                .replyNo(replyDTO.getReplyNo())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .question(question)
                .build();
        return reply;
    }
    //reply객체를 replydto로 변환
    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .replyNo(reply.getReplyNo())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
