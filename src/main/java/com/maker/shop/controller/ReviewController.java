package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.ReviewDTO;
import com.maker.shop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(ReviewDTO reviewDTO, RedirectAttributes redirectAttributes){
        log.info("reviewDTO: "+reviewDTO);
        Long rno = reviewService.register(reviewDTO);
        redirectAttributes.addFlashAttribute("msg",rno);

        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result",reviewService.getList(pageRequestDTO));
    }
}
