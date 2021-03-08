package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/product/")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/prList")
    public void getProductList(PageRequestDTO pageRequestDTO, Model model, String gender, String category) {

        //log.info("pageRequestDTO: " + pageRequestDTO);
        //log.info("gender :" + filter);
        log.info("category : " + category);
        model.addAttribute("category", category);
        model.addAttribute("gender", gender);
        model.addAttribute("result", productService.getProductList(pageRequestDTO, gender, category));

    }

    @GetMapping("/prDetail")
    public void getProductDetail(String pno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("pno: " + pno);

        model.addAttribute("sizeDTO", productService.getProductSizeList(pno));
        model.addAttribute("productDTO", productService.getProduct(pno));
    }

}
