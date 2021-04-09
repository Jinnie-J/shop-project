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
@Log4j2
public class KakaoPayController {

    private final KakaoPay kakaopay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet(){

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(String post_name, String post_amount, String post_price){

        log.info("이름" + post_name);
        log.info("가격" + post_price);
        log.info("수량" + post_amount);
        return "redirect:" + kakaopay.kakaoPayReady(post_name,post_price,post_amount);
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token,@RequestParam("price") String price, Model model){

        model.addAttribute("info",kakaopay.kakaoPayInfo(pg_token,price));
    }

}
