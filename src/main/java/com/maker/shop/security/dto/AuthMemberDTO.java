package com.maker.shop.security.dto;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Log4j2
public class AuthMemberDTO extends User {

    private String email;
    private boolean fromSocial;


    public AuthMemberDTO(String email, String password, Collection<?extends GrantedAuthority> authorities){
        super(email,password, authorities);
        this.email=email;
        this.fromSocial=fromSocial;
    }
}
