package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "sign_up";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
