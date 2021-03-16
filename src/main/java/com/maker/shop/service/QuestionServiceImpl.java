package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.QuestionDTO;
import com.maker.shop.entity.Member;
import com.maker.shop.entity.Product;
import com.maker.shop.entity.Question;
import com.maker.shop.repository.QuestionRepository;
import com.maker.shop.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository repository;

    private final ReplyRepository replyRepository;
    @Override
    public Long register(QuestionDTO dto) {
        log.info(dto);
        Question question = dtoToEntity(dto);

        repository.save(question);

        return question.getQno();
    }

    @Override
    public PageResultDTO<QuestionDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[],QuestionDTO> fn = (en -> entityToDTO((Question)en[0],(Member)en[1],
                (Product)en[2],(Long)en[3]));

        Page<Object[]> result= repository.getQuestionWithReplyCount(pageRequestDTO.getPageable(
                Sort.by("qno").descending()));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public QuestionDTO get(Long qno) {
        Object result = repository.getQuestionByQno(qno);
        Object[] arr = (Object[])result;
        return entityToDTO((Question)arr[0],(Member)arr[1],(Product)arr[2], (Long)arr[3] );
    }

    @Transactional
    @Override
    public void removeWithReplies(Long qno) {
        replyRepository.deleteByQno(qno);
        repository.deleteById(qno);
    }

    @Transactional
    @Override
    public void modify(QuestionDTO questionDTO) {
        Question question = repository.getOne(questionDTO.getQno());

        if(question!= null){
            question.changeTitle(questionDTO.getTitle());
            question.changeContent(questionDTO.getContent());

            repository.save(question);
        }
    }
}
