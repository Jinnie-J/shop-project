package com.maker.shop.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {

    private String email;
    private String password;
    private String name;
    private String birth;
    private String phone;

    private boolean fromSocial;


    public AuthMemberDTO(String username, String password, boolean fromSocial, Collection<?extends GrantedAuthority> authorities){
        super(username,password, authorities);
        this.email=username;
        this.fromSocial=fromSocial;
    }
}
