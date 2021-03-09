package com.maker.shop.controller;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.QuestionDTO;
import com.maker.shop.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/question")
@Log4j2
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list.........."+pageRequestDTO);
        model.addAttribute("result",questionService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }
    @PostMapping("/register")
    public String registerPost(QuestionDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto..."+dto);
        Long qno=questionService.register(dto);
        redirectAttributes.addFlashAttribute("msg",qno);

        return "redirect:/question/list";
    }

    @GetMapping({"/read","modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long qno, Model model){
        log.info("qno:"+qno);
        QuestionDTO questionDTO=questionService.get(qno);
        log.info(questionDTO);
        model.addAttribute("dto",questionDTO);
    }

    @PostMapping("/remove")
    public String remove(long qno, RedirectAttributes redirectAttributes){
        questionService.removeWithReplies(qno);
        redirectAttributes.addFlashAttribute("msg", qno);
        return "redirect:/question/list";
    }

    @PostMapping("/modify")
    public String modify(QuestionDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        questionService.modify(dto);
        redirectAttributes.addFlashAttribute("qno",dto.getQno());

        return "redirect:/question/read";
    }
}
