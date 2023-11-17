package com.jisun.isotope.controller;

import com.jisun.isotope.dto.JoinDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class JoinController {


    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinDto", new JoinDto());

        return "/join";
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute JoinDto joinDto,
                              BindingResult bindingResult,
                              Model model){
        /*오류*/
        if(bindingResult.hasErrors()){
            model.addAttribute("joinDto", new JoinDto());
            return "/join";
        }

        /*널값*/

        return "redirect:/isotope/main";
    }


}
