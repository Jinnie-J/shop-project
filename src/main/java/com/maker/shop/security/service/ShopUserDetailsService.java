package com.maker.shop.security.service;

import com.maker.shop.entity.Member;
import com.maker.shop.repository.MemberRepository;
import com.maker.shop.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException((email)));
    }

    public Member save(AuthMemberDTO authMemberDTO){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        authMemberDTO.setPassword(encoder.encode(authMemberDTO.getPassword()));
        log.info("-----------service:"+authMemberDTO);
        return memberRepository.save(Member.builder()
            .email(authMemberDTO.getEmail())
                .name(authMemberDTO.getName())
                .phone(authMemberDTO.getPhone())
                .fromSocial(false)
                .birth(authMemberDTO.getBirth())
                .auth(authMemberDTO.getAuth())
                .password(authMemberDTO.getPassword())
                .status("일반")
                .point(0L)
                .build());
    }
}
