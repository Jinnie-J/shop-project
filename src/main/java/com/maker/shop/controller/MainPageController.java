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
public class MainPageController {

    private final ProductService productService;

    @GetMapping("/mainPr")
    public void mainPr(PageRequestDTO pageRequestDTO,Model model) {

        model.addAttribute("newProduct", productService.getNewProduct(pageRequestDTO));
        model.addAttribute("saleProduct",productService.getSaleProduct(pageRequestDTO));

//        log.info("메인페이지 상품목록 : " + productService.getNewProduct(pageRequestDTO));
        log.info("할인품목 "+ productService.getSaleProduct(pageRequestDTO));
    }


}
