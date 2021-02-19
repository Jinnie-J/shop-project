package com.maker.shop.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO{

    private String email;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private String auth;
    private boolean fromSocial;
}
