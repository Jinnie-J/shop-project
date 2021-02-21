package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/pickList")
    public void getPickList(PageRequestDTO pageRequestDTO, Model model, Principal principal){
        log.info("pageRequestDTO: "+pageRequestDTO);
        String email=principal.getName();
        log.info("email: "+email);
        log.info(mypageService.getList(pageRequestDTO,email));
        model.addAttribute("result",mypageService.getList(pageRequestDTO, email));
    }

}
