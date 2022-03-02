package com.voytenko.controller;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.helpers.PasswordHelper;
import com.voytenko.model.User;
import com.voytenko.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public Iterable<UserDto> getAll(){
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @PostMapping("/user")
    public UserDto createUser(@Valid @RequestBody CreateUserDto user){
        return UserDto.fromModel(userRepository.save(new User(user.getName(), user.getEmail(), PasswordHelper.encrypt(user.getPassword()))));
    }

    @GetMapping("/user/{id}")
    public UserDto getById(@PathVariable Integer id){
        return userRepository.findById(id).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }




}
