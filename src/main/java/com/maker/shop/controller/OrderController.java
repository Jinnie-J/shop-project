package com.maker.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order/")
public class OrderController {

    @GetMapping("/order")
    public void pa(){

    }
    @PostMapping("/order")
    public void order(Model model, @RequestParam("cntArr") String[] cntArr, @RequestParam("sizeArr") String[] sizeArr, String imgUrl){

        model.addAttribute("cntArr",cntArr);
        model.addAttribute("sizeArr",sizeArr);
        model.addAttribute("imgUrl",imgUrl);

    }
}
