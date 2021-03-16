package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.ProductDTO;
import com.maker.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/product/")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/prList")
    public void getProductList(PageRequestDTO pageRequestDTO, Model model, String gender, String category, String sortType, String brand) {

        //log.info("gender :" + filter);
        //log.info("category : " + sortType);
        model.addAttribute("category", category);
        model.addAttribute("gender", gender);
        model.addAttribute("sortType", sortType);
        model.addAttribute("result", productService.getProductList(pageRequestDTO, gender, category, sortType));
    }

    @GetMapping("/prDetail")
    public void getProductDetail(String pno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("pno: " + pno);

        model.addAttribute("sizeDTO", productService.getProductSizeList(pno));
        model.addAttribute("productDTO", productService.getProduct(pno));
    }

}
