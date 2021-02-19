package com.maker.shop.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity implements UserDetails {

    @Id
    private String email;

    private String password;

    private String name;

    private String birth;

    private Long point;

    private String phone;

    private String status;

    private String auth;

    private boolean fromSocial;

    @Builder
    public Member(String email, String password, String name,
                  String birth, String phone, String auth, boolean fromSocial){
        this.email=email;
        this.password=password;
        this.name=name;
        this.birth=birth;
        this.phone=phone;
        this.auth=auth;
        this.fromSocial=fromSocial;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles=new HashSet<>();
        for(String role :auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
