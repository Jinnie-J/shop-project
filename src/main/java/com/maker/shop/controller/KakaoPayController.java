package com.maker.shop.controller;

import com.maker.shop.service.KakaoPay;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KakaoPayController {

    private KakaoPay kakaopay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet(){

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(){

        //return "redirect:" + kakaopay.kakaoPayReady();
        return "https://kapi.kakao.com/v1/payment/ready";
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model){

        model.addAttribute("info",kakaopay.kakaoPayInfo(pg_token));
    }

}