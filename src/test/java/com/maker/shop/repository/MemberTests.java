package com.maker.shop.repository;

import com.maker.shop.entity.Member;
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

        IntStream.rangeClosed(1,1).forEach(i-> {
            Member member= Member.builder()
                    .email("usertest"+i+"@maker.com")
                    .name("사용자"+i)
                    .phone("010-1234-5678")
                    .fromSocial(false)
                    .birth("2021/02/16")
                    .password(passwordEncoder.encode("1111"))
                    .build();

            repository.save(member);
        });
    }

    @Test
    public void testRead(){

        Optional<Member> result = repository.findByEmail("user1@maker.com");
        Member member=result.get();
        System.out.println(member);
    }
}
