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

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("MemberUserDetailsService loadUserByUsername "+ username);

        Optional<Member> result = memberRepository.findByEmail(username,false);
        if(!result.isPresent()){
            throw new UsernameNotFoundException("Check Email or Social");
        }
        Member member=result.get();

        log.info("-------------------------");
        log.info(member);

        AuthMemberDTO authMember=new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );
        authMember.setName(member.getName());
        authMember.setFromSocial(member.isFromSocial());

        return authMember;
    }

    public Member save(AuthMemberDTO authMemberDTO){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        authMemberDTO.setPassword(encoder.encode(authMemberDTO.getPassword()));

        return memberRepository.save(Member.builder()
            .email(authMemberDTO.getEmail())
                .name(authMemberDTO.getName())
                .phone(authMemberDTO.getPhone())
                .fromSocial(false)
                .birth(authMemberDTO.getBirth())
                .password(authMemberDTO.getPassword())
                .build());
    }
}
