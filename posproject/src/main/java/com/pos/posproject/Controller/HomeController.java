package com.pos.posproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //가장 기본 페이지의 의미, 즉 루트페이지 위치
    public String home() {
        return "springHome";
    }
}
