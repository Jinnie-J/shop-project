package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    @GetMapping("/prList")
    public void getProductList(PageRequestDTO pageRequestDTO, Model model,String gender,String category) {

        //log.info("pageRequestDTO: " + pageRequestDTO);
        //log.info("gender :" + filter);
        model.addAttribute("gender", gender);
        model.addAttribute("result", productService.getProductList(pageRequestDTO,gender,category));

    }

    @GetMapping("/mainPr")
    public void mainPr(){

    }

}
