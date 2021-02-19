package com.maker.shop.controller;


import com.maker.shop.security.dto.AuthMemberDTO;
import com.maker.shop.security.service.ShopUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final ShopUserDetailsService userService;

    @GetMapping("/all")
    public void exAll(){
        log.info("exAll............");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMember){
        log.info("exMember..........");
        log.info("---------------------");
        log.info(authMember);
    }
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin...........");
    }


    @PostMapping("/member")
    public String signup(AuthMemberDTO authMemberDTO){
        userService.save(authMemberDTO);
        return "redirect:/login";
    }

    @GetMapping("/main")
    public void main(){
        log.info("main..........");
    }

    @GetMapping("/login")
    public void login(){log.info("login............");
    }

    @GetMapping("/signup")
    public void signup(){log.info("signup............");
    }

    @GetMapping(value="/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }
}
