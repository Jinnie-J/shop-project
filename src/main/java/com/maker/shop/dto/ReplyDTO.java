package com.maker.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private Long replyNo;
    private String text;
    private String replyer;
    private Long qno;
    private LocalDateTime regDate, modDate;
}
