package com.maker.shop.repository;

import com.maker.shop.entity.Member;
import com.maker.shop.entity.MemberRole;
import com.maker.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        //1-80까지는 USER만 지정
        //81-90까지는 USER,ADMIN

        IntStream.rangeClosed(1,10).forEach(i-> {
            Member member= Member.builder()
                    .email("user"+i+"@maker.com")
                    .point(1000L)
                    .phone("010-1234-5678")
                    .status("일반")
                    .grade("등급")
                    .fromSocial(false)
                    .birth("2021/02/16")
                    .password(passwordEncoder.encode("1111"))
                    .build();

            member.addMemberRole(MemberRole.USER);
            if(i>80){
                member.addMemberRole(MemberRole.ADMIN);
            }
            repository.save(member);
        });
    }

    @Test
    public void testRead(){

        Optional<Member> result = repository.findByEmail("user1@maker.com",false);
        Member member=result.get();
        System.out.println(member);
    }
}
