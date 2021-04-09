package com.maker.shop.controller;

import com.maker.shop.service.KakaoPay;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPay kakaopay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet(){

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(){

        return "redirect:" + kakaopay.kakaoPayReady();
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model){

        model.addAttribute("info",kakaopay.kakaoPayInfo(pg_token));
    }

}
