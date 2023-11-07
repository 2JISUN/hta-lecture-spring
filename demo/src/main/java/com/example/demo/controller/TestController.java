package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //*타임리프 추가 후 *html파일 리턴
@Slf4j //lombok 추가 후
//@RequestMapping("/user")
public class TestController {
    @GetMapping("/")
    public String home(@RequestParam(required = true, defaultValue = "0") int age,
                       @RequestParam(required = false) String name) {
        log.info("age==={}===name==={}", age, name);
        return "index"; //html 디스패치
    }


    @PostMapping("/post/{age}") //url로 들어올 수 없음
    @ResponseBody //데이터 전달 형식 : Json
    public String pathTest(@PathVariable int age) { // /post/{age}/{name} -> url로 인식하지 않고 변수로써 인식함
        log.info("age==={}", age);
        return "index"; //html 디스패치
    }


    @PutMapping("/put")
    @ResponseBody //
    public String put(){
        return "put";
    }


    @PatchMapping("/patch")
    @ResponseBody //
    public String patch(){
        return "patch";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody //
    public String delete(@PathVariable int id){
        log.info("id==={}", id);
        return "delete";
    }


}