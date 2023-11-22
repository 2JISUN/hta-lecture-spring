package com.jisun.isotope.controller;

import com.jisun.isotope.service.IsotopeService;
import com.jisun.isotope.dto.IsotopeDto;
import com.jisun.isotope.enums.Category;
import com.jisun.isotope.service.IsotopeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/isotope")
@Slf4j
@RequiredArgsConstructor
public class IsotopeController {

    private final IsotopeService isotopeService;
    private Category categoryArr[] = {Category.SKETCH,Category.PAINT,Category.PHOTO};
    List<Category> categoryList = Arrays.asList(categoryArr);
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
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("isotopeDto",isotopeDto);

        return "/insert";
    }

    //validation
    @PostMapping("/insert")
    public String insertProcess(@Valid @ModelAttribute IsotopeDto isotopeDto,
                                BindingResult bindingResult,
                                Model model ) {
        if(isotopeDto.getFile().isEmpty()) {
            model.addAttribute("categoryList",categoryList);
            model.addAttribute("isotopeDto",isotopeDto);
            bindingResult.addError(new FieldError("fileError","file","이미지를 선택해 주세요."));
            return "/insert";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("categoryList",categoryList);
            model.addAttribute("isotopeDto",isotopeDto);
            return "/insert";
        }
        int result = isotopeService.insertIsotope(isotopeDto);
        log.info("result==={}",result);
        return "redirect:/";
    }


}
