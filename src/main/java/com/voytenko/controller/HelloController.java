package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("")
    public String getIndexPage() {
        return "index";
    }

    @ApiOperation(value = "Регистрация нового пользователя", notes = "Возвращает html-страницу")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The page was not found")
    })
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
