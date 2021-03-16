package com.maker.shop.repository;

import com.maker.shop.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q, u from Question q left join q.user u where q.qno =:qno")
    Object getQuestionWithUser(@Param("qno") Long qno);

    @Query("select q, r from Question q left join Reply r on r.question = q where q.qno = :qno")
    List<Object[]> getQuestionWithReply(@Param("qno") Long qno);

    @Query(value=" select q, u, p, count(r) " +
            " from Question q " +
            " left join q.user u " +
            " left join Product p on q.product= p" +
            " left join Reply r on r.question = q " +
            " group by q ",
            countQuery="select count(q) from Question q ")
    Page<Object[]> getQuestionWithReplyCount(Pageable pageable);

    @Query("select q, u, p, count(r) " +
            " from Question q left join q.user u " +
            " left outer join Product p on q.product = p " +
            " left outer join Reply r on r.question = q " +
            " where q.qno = :qno")
    Object getQuestionByQno(@Param("qno") Long qno);
}
