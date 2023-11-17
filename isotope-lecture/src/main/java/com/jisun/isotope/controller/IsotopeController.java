package com.jisun.isotope.controller;

import com.jisun.isotope.dto.IsotopeDto;
import com.jisun.isotope.service.IsotopeService;
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

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/isotope")
@Slf4j
@RequiredArgsConstructor
public class IsotopeController {

    private final IsotopeService isotopeService;
    private String category[] = {"sketch","photo","paint"};
    private List<String> categoryList = Arrays.asList(category);

    @GetMapping({"/","/index","/main",""})
    public String index(Model model) {
        List<IsotopeDto> boardList = isotopeService.getAllList();
        model.addAttribute("boardList",boardList);
        model.addAttribute("categoryList",categoryList);



        return "/index";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        IsotopeDto isotopeDto = new IsotopeDto();

        model.addAttribute("isotopeDto",isotopeDto);
        model.addAttribute("categoryList",categoryList);
        return "/insert";
    }

    //validation
    @PostMapping("/insert")
    public String insertProcess(@Valid @ModelAttribute IsotopeDto isotopeDto,
                                BindingResult bindingResult,
                                Model model
                                ) {
        if(bindingResult.hasErrors()){
            model.addAttribute("isotopeDto",isotopeDto);
            return "/insert";
        }
        int result = isotopeService.insertIsotope(isotopeDto);
        log.info("result==={}",result);
            return "redirect:/";
    }


}
