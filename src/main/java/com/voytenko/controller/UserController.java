package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.services.UserService;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseBody
    public Iterable<UserDto> getAll(){
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        return "sign_up_success";
    }

    @GetMapping(("/verification"))
    public String verify(@Param("code") String code){
        if (userService.verify(code)){
            return "verification_success";
        } else{
            return "verification_failed";
        }
    }

}
