package com.voytenko.services;

import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById (Integer id);
    List<UserDto> findAll();
    UserDto signUp(CreateUserDto createUserDto, String url);
    UserDto findByEmail(String email);
    boolean verify (String verificationCode);
    void sendVerificationMail(String email, String url, String name, String code);
}
