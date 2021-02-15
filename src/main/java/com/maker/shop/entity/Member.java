package com.maker.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNo;

    private String email;

    private String password;

    private Date birth;

    private Long point;

    private String phone;

    private String status;

    private String grade;

}
