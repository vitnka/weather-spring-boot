package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
//
//    @PostMapping("/user")
//    @ResponseBody
//    public UserDto createUser(@Valid @RequestBody CreateUserDto user){
//        return userService.save(user);
//    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("/user/{email}")
    @ResponseBody
    public UserDto getByEmail(@PathVariable String email){
        return userService.findByEmail(email);
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
