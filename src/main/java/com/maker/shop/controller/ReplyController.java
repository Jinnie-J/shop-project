package com.maker.shop.controller;

import com.maker.shop.dto.ReplyDTO;
import com.maker.shop.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping(value = "/question/{qno}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByQuestion(@PathVariable("qno") Long qno){
        log.info("qno: "+qno);
        return new ResponseEntity<>( replyService.getList(qno), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        Long replyNo = replyService.register(replyDTO);
        return new ResponseEntity<>(replyNo,HttpStatus.OK);
    }
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<String> remove(@PathVariable("replyNo")Long replyNo){
        log.info("REPLYNO: "+replyNo);
        replyService.remove(replyNo);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @PutMapping("/{replyNo}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}
