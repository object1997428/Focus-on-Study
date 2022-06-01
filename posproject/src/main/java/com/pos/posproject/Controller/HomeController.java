package com.pos.posproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {





    @GetMapping("/search")
    public String search_group(){
        System.out.println("함수가 되긴함");
        return "find_group";}

    @GetMapping("/group")
    public String group_page(){
        System.out.println("함수가 되긴함");
        return "group_page";}

    @GetMapping("/spring")
    public String springhome(){
        return "springHome.html";
    }
}
