package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.helpers.PasswordHelper;
import com.voytenko.model.User;
import com.voytenko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public Iterable<UserDto> getAll(){
        return userService.findAll();
    }

    @PostMapping("/user")
    @ResponseBody
    public UserDto createUser(@Valid @RequestBody CreateUserDto user){
        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto) {
        userService.save(userDto);
        return "sign_up_success";
    }



}
