package com.maker.shop.controller;

import com.maker.shop.dto.ProductDTO;
import com.maker.shop.dto.ProductSizeDTO;
import com.maker.shop.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order/")
public class OrderController {

    @GetMapping("/order")
    public void pa(){

    }
    @PostMapping("/order")
    public void order(Model model, @RequestParam("cntArr") String[] cntArr, @RequestParam("sizeArr") String[] sizeArr, String imgUrl,Long price,String name, String brand){

        List<ProductSizeDTO> sizeDTO = new ArrayList<ProductSizeDTO>();

        for(int i=0; i<sizeArr.length; i++){

            sizeDTO.add(new ProductSizeDTO(Long.parseLong(cntArr[i]),Long.parseLong(sizeArr[i])));
        }

        ProductDTO product = new ProductDTO();
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);

        log.info("product : "+ product);

        model.addAttribute("sizeDTO",sizeDTO);
        model.addAttribute("imgUrl",imgUrl);
        model.addAttribute("product",product);

    }
}
