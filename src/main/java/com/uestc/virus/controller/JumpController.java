package com.uestc.virus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JumpController {

    @GetMapping("index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("test")
    public String toTest(){
        return "test";
    }

}
