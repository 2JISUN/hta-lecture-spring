package com.example.demo.controller;

import com.example.demo.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Json
public class JsonController {
    @GetMapping("/response/json")
    public Person responseJson(){
        return Person
                .builder()
                .tel("010-5181-2985")
                .age(10)
                .name("이지선")
                .address("은하계")
                .build();
    }
}


