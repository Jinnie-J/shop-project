package com.maker.shop.service;


import com.maker.shop.dto.ReplyDTO;
import com.maker.shop.entity.Question;
import com.maker.shop.entity.Reply;
import com.maker.shop.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getReplyNo();
    }

    @Override
    public List<ReplyDTO> getList(Long qno) {
        List<Reply> result = replyRepository.getRepliesByQuestionOrderByReplyNo(Question.builder().qno(qno).build());
        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long replyNo) {
        replyRepository.deleteById(replyNo);
    }
}
