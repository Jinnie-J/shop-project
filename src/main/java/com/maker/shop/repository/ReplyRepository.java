package com.maker.shop.repository;

import com.maker.shop.entity.Question;
import com.maker.shop.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.question.qno =:qno ")
    void deleteByQno(Long qno);

    //게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByQuestionOrderByReplyNo(Question question);
}
