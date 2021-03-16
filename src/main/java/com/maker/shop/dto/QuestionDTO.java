package com.maker.shop.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long qno;
    private String title;
    private String content;
    private String pno;
    private String writerEmail;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount;
}
