package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.ldap.Rdn;
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
        log.info(mypageService.getPickList(pageRequestDTO,email));
        model.addAttribute("result",mypageService.getPickList(pageRequestDTO, email));
    }

    @GetMapping("/removePL")
    public String removePL(long pickNo, RedirectAttributes redirectAttributes){
        mypageService.removePL(pickNo);
        redirectAttributes.addFlashAttribute("msg",pickNo);
        return "redirect:/mypage/pickList";
    }

    @GetMapping("/cart")
    public void getCartList(PageRequestDTO pageRequestDTO, Model model, Principal principal){
        String email=principal.getName();
        log.info(mypageService.getCartList(pageRequestDTO,email));
        model.addAttribute("result",mypageService.getCartList(pageRequestDTO, email));
    }

    @GetMapping("/removeCart")
    public String removeCart(long cno, RedirectAttributes redirectAttributes){
        mypageService.removeCart(cno);
        redirectAttributes.addFlashAttribute("msg",cno);
        return "redirect:/mypage/cart";
    }

}
