package com.maker.shop.controller;


import com.maker.shop.entity.Member;
import com.maker.shop.security.dto.AuthMemberDTO;
import com.maker.shop.security.service.ShopUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final ShopUserDetailsService userService;


    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin...........");
    }


    @PostMapping("/user")
    public String signup(AuthMemberDTO authMemberDTO){
        userService.save(authMemberDTO);
        return "redirect:/member/login";
    }

    @GetMapping("/mypage")
    public void mypage(Model model){
        Member member=(Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("member:"+member);

        model.addAttribute("member",member);
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

        return "redirect:/member/login";
    }
}
