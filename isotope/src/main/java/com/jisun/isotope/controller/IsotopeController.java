package com.jisun.isotope.controller;

import com.jisun.isotope.dto.IsotopeDto;
import com.jisun.isotope.enums.CategoryEnums;
import com.jisun.isotope.service.IsotopeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private CategoryEnums categoryEnumsArray[] = {CategoryEnums.SKETCH,
                                                  CategoryEnums.PAINT,
                                                  CategoryEnums.PHOTO};
    List<CategoryEnums> categoryEnumsList = Arrays.asList(categoryEnumsArray);



    /*main*/
    @GetMapping({"/","/index","/main",""})
    public String index(Model model) {
        List<IsotopeDto> boardList = isotopeService.getAllList();
        model.addAttribute("boardList",boardList);
        model.addAttribute("categoryEnumsList",categoryEnumsList);

        return "/index";
    }






    @GetMapping("/insert")
    public String insert(Model model) {
        IsotopeDto isotopeDto = new IsotopeDto();

        model.addAttribute("isotopeDto",isotopeDto);
        model.addAttribute("categoryEnumsList",categoryEnumsList);
        return "/insert";
    }








    @PostMapping("/insert")
    public String insertProcess(@Valid @ModelAttribute IsotopeDto isotopeDto,
                                BindingResult bindingResult, //순서가 중요함 dto 뒤에 binding
                                Model model
                                ) {
        //null검증
        if(isotopeDto.getFile().isEmpty()){
            model.addAttribute("isotopeDto",isotopeDto);
            model.addAttribute("categoryEnumsList",categoryEnumsList);
            bindingResult.addError
                    (new FieldError("fileError", "file", "이미지를 선택해 주세요."));
            return "/insert";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("isotopeDto",isotopeDto);
            model.addAttribute("categoryEnumsList",categoryEnumsList);
            return "/insert";
        }


        int result = isotopeService.insertIsotope(isotopeDto);
        log.info("result==={}",result);
            return "redirect:/isotope";
    }


}
