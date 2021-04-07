package com.maker.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long rno;
    private String title;
    private String content;
    private String pno;
    private String writerEmail;
    private String writerName;
    private int grade;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder.Default
    private List<ReviewImageDTO> imageDTOList=new ArrayList<>();

    public void changeGrade(int grade){
        this.grade=grade;
    }
}
