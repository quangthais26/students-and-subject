package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public String home(Model model){
        model.addAttribute("content","home");
        model.addAttribute("title","Trang chu");
        return "layout/main";
    }
    @GetMapping("about")
    public String about(Model model){
        model.addAttribute("content","about");
        model.addAttribute("title","Ve chung toi");

        return "layout/main";
    }
}
