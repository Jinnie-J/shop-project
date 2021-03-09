package com.maker.shop.service;

import com.maker.shop.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService service;

    @Test
    public void testGetList(){
        Long qno=4L;
        List<ReplyDTO> replyDTOList=service.getList(qno);
        replyDTOList.forEach( replyDTO -> System.out.println(replyDTO));
    }
}
